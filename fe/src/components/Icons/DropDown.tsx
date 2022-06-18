import React from "react";
import DefaultIconProps from "./DefaultIconProps";

function DropDownIcon({ size = { width: "8", height: "4" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M1 1L5 5L9 1" stroke="#6E7191" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" />
    </svg>
  );
}

export default DropDownIcon;
