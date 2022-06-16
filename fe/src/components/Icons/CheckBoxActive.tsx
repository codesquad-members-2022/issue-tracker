import DefaultIconProps from "./DefaultIconProps";

function CheckBoxActiveIcon({ size = { width: "16", height: "16" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
      <rect width="16" height="16" rx="2" fill="#14142B" />
      <path
        d="M10.6667 6L7 9.67333L5 7.67333"
        stroke="#FEFEFE"
        stroke-width="1.6"
        stroke-linecap="round"
        stroke-linejoin="round"
      />
    </svg>
  );
}

export default CheckBoxActiveIcon;
