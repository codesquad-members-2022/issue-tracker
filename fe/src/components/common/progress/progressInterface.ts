export interface ProgressData {
  value: string;
  count?: {
    open: string;
    close: string;
  };
}

export interface ProgressProps {
  width?: number;
  height?: number;
  data: ProgressData;
}

export interface StyledProgressProps {
  width: number;
}
