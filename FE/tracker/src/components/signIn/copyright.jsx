import * as React from "react";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";

function Copyright(props) {
    return (
        <Typography
            variant="body2"
            color="text.secondary"
            align="center"
            {...props}
        >
            {"Copyright © "}
            <Link color="inherit" href="https://mui.com/">
                Thanks Material-UI!
            </Link>
            {" - nori, sonya "}
            {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

export default Copyright;
