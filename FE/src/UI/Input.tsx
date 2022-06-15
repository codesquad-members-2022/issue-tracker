import React from 'react';

type InputPropType = {
  label: string;
  info: Record<string, string>;
};

export const InputWithRef = React.forwardRef<HTMLInputElement, InputPropType>(
  (props, ref) => {
    return (
      <>
        <label htmlFor={props.label} />
        <input ref={ref} {...props.info} />
      </>
    );
  },
);

export const Input = (props: InputPropType) => {
  return (
    <>
      <label htmlFor={props.label} />
      <input {...props.info} />
    </>
  );
};
