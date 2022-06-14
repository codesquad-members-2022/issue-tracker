import React from 'react';

type InputPropType = {
  label: string;
  info: Record<string, string>;
};

const Input = React.forwardRef<HTMLInputElement, InputPropType>(
  (props, ref) => {
    return (
      <>
        <label htmlFor={props.label} />
        <input ref={ref} {...props.info} />
      </>
    );
  },
);

export default Input;
