import * as React from "react";
import axios from "axios";

// MUI Material
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import Switch from "@mui/material/Switch";

// MUI Theme
// import { ThemeProvider } from "@mui/material/styles";
import { listTheme } from "../../mui-style/muiTheme";

// Types
import { Data, Order } from "./issueLIst.types";

// Functions
import {
    descendingComparator,
    getComparator,
    stableSort,
    createData,
} from "./issueList.function.tsx";

// Table Parts
import EnhancedTableHead from "./issueListHead.tsx";
import { EnhancedTableToolbar } from "./issueListToolbar.tsx";
import { setConstantValue } from "typescript";

// const rows = [
//     createData("dummy1", 305, 3.7, 67, 4.3),
//     createData("dummy2", 452, 25.0, 51, 4.9),
//     createData("dummy3", 262, 16.0, 24, 6.0),
// ];

export default function EnhancedTable() {
    const [order, setOrder] = React.useState<Order>("asc");
    const [orderBy, setOrderBy] = React.useState<keyof Data>("writer");
    const [selected, setSelected] = React.useState<readonly string[]>([]);
    const [page, setPage] = React.useState(0);
    const [dense, setDense] = React.useState(false);
    const [rows, setRows] = React.useState([]);
    const [rowsPerPage, setRowsPerPage] = React.useState(5);

    const [isLoading, setIsLoading] = React.useState(false);

    const handleRequestSort = (
        event: React.MouseEvent<unknown>,
        property: keyof Data
    ) => {
        const isAsc = orderBy === property && order === "asc";
        setOrder(isAsc ? "desc" : "asc");
        setOrderBy(property);
    };

    const handleSelectAllClick = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        // if (event.target.checked) {
        //     const newSelecteds = rows.map((n) => n.name);
        //     setSelected(newSelecteds);
        //     return;
        // }
        // setSelected([]);

        console.log("handleSelectAllClick");
    };

    const handleClick = (event: React.MouseEvent<unknown>, name: string) => {
        const selectedIndex = selected.indexOf(name);
        let newSelected: readonly string[] = [];

        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selected, name);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selected.slice(1));
        } else if (selectedIndex === selected.length - 1) {
            newSelected = newSelected.concat(selected.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                selected.slice(0, selectedIndex),
                selected.slice(selectedIndex + 1)
            );
        }

        setSelected(newSelected);
    };

    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleChangeDense = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDense(event.target.checked);
    };

    const isSelected = (name: any) => selected.indexOf(name) !== -1;

    const fetchData = () => {
        return axios
            .get(
                "https://313952d4-dd03-4472-a26f-e82b8c0038da.mock.pstmn.io/api/issues?page=1&status=OPEN"
            )
            .then((res: any) => {
                const corridor = res.data.issues.map((e) =>
                    createData(
                        e.title,
                        e.createdAt,
                        e.writer.loginId,
                        e.writer.loginId
                    )
                );

                setRows(corridor);

                console.log("isseus downloaded : ", res.data);
                console.log("corridor", corridor);
                console.log("rows", rows);
            })
            .catch((error) => console.error(`Error: ${error}`));
    };

    React.useEffect(() => {
        // Postman Api 문서에서 데이터 받아오기
        console.log("데이터중복방지?");
        fetchData();
        setIsLoading(true);
    }, []);

    if (isLoading) {
        // Avoid a layout jump when reaching the last page with empty rows.
        const emptyRows =
            page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0;

        return (
            <Box sx={{ width: "800px", mt: 5, marginLeft: 10 }}>
                <Paper sx={{ width: "100%", mb: 2 }}>
                    <EnhancedTableToolbar numSelected={selected.length} />
                    <TableContainer>
                        <Table
                            sx={{ minWidth: 750 }}
                            aria-labelledby="tableTitle"
                            size={dense ? "small" : "medium"}
                        >
                            <EnhancedTableHead
                                numSelected={selected.length}
                                order={order}
                                orderBy={orderBy}
                                onSelectAllClick={handleSelectAllClick}
                                onRequestSort={handleRequestSort}
                                rowCount={rows.length}
                            />
                            <TableBody>
                                {rows
                                    .slice(
                                        page * rowsPerPage,
                                        page * rowsPerPage + rowsPerPage
                                    )
                                    .map((row, index) => {
                                        const isItemSelected = isSelected(
                                            row.name
                                        );
                                        const labelId = `enhanced-table-checkbox-${index}`;

                                        return (
                                            <TableRow hover>
                                                <TableCell padding="checkbox">
                                                    <Checkbox
                                                        color="primary"
                                                        checked={isItemSelected}
                                                        inputProps={{
                                                            "aria-labelledby":
                                                                labelId,
                                                        }}
                                                    />
                                                </TableCell>
                                                <TableCell>
                                                    {row.name}
                                                </TableCell>
                                                <TableCell align="right">
                                                    {row.issueDate}
                                                </TableCell>
                                                <TableCell align="right">
                                                    {row.writer}
                                                </TableCell>
                                                <TableCell align="right">
                                                    {row.reviewer}
                                                </TableCell>
                                            </TableRow>
                                        );
                                    })}
                                {emptyRows > 0 && (
                                    <TableRow
                                        style={{
                                            height:
                                                (dense ? 33 : 53) * emptyRows,
                                        }}
                                    >
                                        <TableCell colSpan={6} />
                                    </TableRow>
                                )}
                            </TableBody>
                        </Table>
                    </TableContainer>
                    <TablePagination
                        rowsPerPageOptions={[5, 10, 25]}
                        component="div"
                        count={rows.length}
                        rowsPerPage={rowsPerPage}
                        page={page}
                        onPageChange={handleChangePage}
                        onRowsPerPageChange={handleChangeRowsPerPage}
                    />
                </Paper>
                <FormControlLabel
                    control={
                        <Switch checked={dense} onChange={handleChangeDense} />
                    }
                    label="Dense padding"
                />
            </Box>
        );
    }
}
