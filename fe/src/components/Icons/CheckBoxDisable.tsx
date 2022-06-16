import DefaultIconProps from "./DefaultIconProps";

function CheckBoxDisableIcon({ size = { width: "16", height: "16" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
      <rect width="16" height="16" rx="2" fill="#14142B" />
      <path d="M6 8H10" stroke="#FEFEFE" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round" />
    </svg>
  );
}

export default CheckBoxDisableIcon;
