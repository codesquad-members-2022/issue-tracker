import React, { useEffect, useRef } from "react";

function Matrix() {
    const canvasRef = useRef(null);
    const canvasWidth = 1500;
    const canvasHeight = 800;
    const cols = Math.floor(canvasWidth / 10) + 1;
    console.log(cols);
    const ypos = Array(cols).fill(canvasHeight);

    const korean = "이슈트레커에캔버스넣기진짜어렵네";
    const latin = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const nums = "0123456789";

    const alphabet = korean + latin + nums;
    const fontSize = 8;

    const drawMatrix = () => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext("2d");

        canvas.width = canvasWidth;
        canvas.height = canvasHeight; // 에러는 안나지만 크기 조절이 안되네...
        // ctx.rotate(-30 * (Math.PI / 180));
        ctx.fillStyle = "#000000";
        ctx.fillRect(0, 0, canvasWidth, canvasHeight);

        ctx.fillStyle = "rgba(0,0,0,0.05)";
        ctx.fillStyle = "#0f0";

        ctx.font = fontSize + "monospace";

        ypos.forEach((y, ind) => {
            const text = alphabet.charAt(Math.floor(Math.random() * 8));
            // const text = String.fromCharCode(Math.random() * 10);
            const x = ind * 20;
            ctx.fillText(text, x, y);
            if (y * fontSize > canvasHeight && Math.random() > 0.97)
                ypos[ind] = 0;
            // if (y > 100 + Math.random() * 10000) ypos[ind] = 0;
            else ypos[ind] = y + 20;
        });
    };

    useEffect(() => {
        const intervalPoint = setInterval(() => {
            console.log("matrix drawing");
            drawMatrix();
        }, 100);
        return () => clearInterval(intervalPoint);
    }, []);

    return <canvas ref={canvasRef} />;
}

export default Matrix;
