// import * as React, {} from "react";
import React, { useMemo } from "react";
// import Matrix from "./canvas.jsx";

// Material UI
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";

import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Copyright from "./copyright.jsx";
import { matrixTheme } from "../../mui-style/theme.jsx";

export default function SignIn(props) {
    const { req: Request, res: Response, next: NextFunction } = props;
    // const code = req.query.code;
    // const pathUrl = req.query.state;

    // if (req.query.error) {
    //     return res.redirect(`${config.get("origin")}/login`);
    // }
    // if (!code) {
    //     return next(new Error("Autorization code not provided!", 401));
    // }
    // const {access_token} = await getToken({code});

    // const user = await findAndUpdateUser({email}, {
    //     email,
    //     photo:avatar_url,
    //     name:login,
    //     provider:'Github',
    //     verified:true,
    // },
    // { runValidators: false, new: true, upsert: true }
    // );

    // if (!user) {
    //   return res.redirect(`${config.get<string>('origin')}/oauth/error`);
    // }

    const loginUri =
        "https://github.com/login/oauth/authorize?client_id=2f3993bd2491df08d37b";

    const handleGithubLogin = (event) => {
        event.preventDefault();
        console.log("success");
    };

    return (
        <ThemeProvider theme={matrixTheme}>
            <Container component="main" maxWidth="xs" color="primary">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: "primary.main" }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Issue Tracker?
                    </Typography>
                    <Box>
                        <Link href={loginUri}>
                            <Button
                                fullWidth
                                variant="contained"
                                color="primary"
                                sx={{ mt: 3 }}
                            >
                                GitHub Login 깃헙 로그인
                            </Button>
                        </Link>
                    </Box>
                </Box>
                <Copyright sx={{ mt: 8, mb: 4 }} />
            </Container>
        </ThemeProvider>
    );
}
