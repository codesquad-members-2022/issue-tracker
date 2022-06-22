export interface Data {
    name: string;
    issueDate: number;
    writer: number;
    reviewer: number;
}

export interface HeadCell {
    disablePadding: boolean;
    id: keyof Data;
    label: string;
    numeric: boolean;
}

export interface EnhancedTableProps {
    numSelected: number;
    onRequestSort: (
        event: React.MouseEvent<unknown>,
        property: keyof Data
    ) => void;
    onSelectAllClick: (event: React.ChangeEvent<HTMLInputElement>) => void;
    order: Order;
    orderBy: string;
    rowCount: number;
}
export interface EnhancedTableToolbarProps {
    numSelected: number;
}

export type Order = "asc" | "desc";
