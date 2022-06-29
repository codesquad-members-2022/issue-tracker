import icons from '@/assets/icons/svgs';

export type IconNameType = keyof typeof icons;

type IconProps = {
  iconName: IconNameType;
  width?: number;
  height?: number;
  stroke?: string;
  fill?: string;
  onClick?: (() => void) | null;
};

function Icon({
  iconName,
  width = 16,
  height = 16,
  stroke = 'none',
  fill = 'none',
  onClick = null
}: IconProps) {
  const IconSvg = icons[iconName];
  return (
    <IconSvg
      width={width}
      height={height}
      stroke={stroke}
      fill={fill}
      onClick={onClick}
    />
  );
}

export default Icon;
