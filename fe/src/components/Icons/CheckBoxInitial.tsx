import DefaultIconProps from "./DefaultIconProps";

function CheckBoxInitialIcon({ size = { width: "16", height: "16" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
      <rect x="0.8" y="0.8" width="14.4" height="14.4" rx="1.2" fill="#FEFEFE" stroke="#14142B" strokeWidth="1.6" />
    </svg>
  );
}

export default CheckBoxInitialIcon;
