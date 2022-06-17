import React from "react";

import DefaultIconProps from "./DefaultIconProps";

function GripIcon({ size = { width: "18", height: "18" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M9 17L17 9" stroke="#A0A3BD" />
      <path d="M1 17L17 1" stroke="#A0A3BD" />
    </svg>
  );
}

export default GripIcon;
