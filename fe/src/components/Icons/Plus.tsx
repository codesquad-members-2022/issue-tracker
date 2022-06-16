import DefaultIconProps from "./DefaultIconProps";

function PlusIcon({ size = { width: "16", height: "16" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M8 3.33334V12.6667" stroke="#111111" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round" />
      <path d="M3.33325 8H12.6666" stroke="#111111" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round" />
    </svg>
  );
}

export default PlusIcon;
