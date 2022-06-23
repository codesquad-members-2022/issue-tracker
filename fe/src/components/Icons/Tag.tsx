import React from "react";
import DefaultIconProps from "./DefaultIconProps";

function TagIcon({ size = { width: "16", height: "16" }, color }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path
        d="M4.66659 4.66668H4.67325M13.7266 8.94001L8.94659 13.72C8.82275 13.844 8.6757 13.9423 8.51384 14.0094C8.35198 14.0765 8.17847 14.1111 8.00325 14.1111C7.82803 14.1111 7.65453 14.0765 7.49267 14.0094C7.3308 13.9423 7.18375 13.844 7.05992 13.72L1.33325 8.00001V1.33334H7.99992L13.7266 7.06001C13.9749 7.30983 14.1143 7.64776 14.1143 8.00001C14.1143 8.35226 13.9749 8.69019 13.7266 8.94001Z"
        stroke={color}
        strokeWidth="1.6"
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </svg>
  );
}

export default TagIcon;
