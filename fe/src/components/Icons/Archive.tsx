import DefaultIconProps from "./DefaultIconProps";

function ArchiveIcon({ size = { width: "16", height: "16" } }: DefaultIconProps) {
  return (
    <svg width={size.width} height={size.height} viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
      <g clip-path="url(#clip0_85_559)">
        <path
          d="M14 5.33334V14H2V5.33334"
          stroke="#14142B"
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          d="M15.3334 2H0.666748V5.33333H15.3334V2Z"
          stroke="#14142B"
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          d="M6.66675 8H9.33341"
          stroke="#14142B"
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
      </g>
      <defs>
        <clipPath id="clip0_85_559">
          <rect width="16" height="16" fill="white" />
        </clipPath>
      </defs>
    </svg>
  );
}

export default ArchiveIcon;
