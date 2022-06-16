import DefaultIconProps from "./DefaultIconProps";

function UserImageIcon({ size = { width: "44", height: "44" }, src }: DefaultIconProps) {
  return <img src={src} alt="사용자 썸네일"></img>;
}

export default UserImageIcon;
