import React from 'react';

type CheckBoxProps = {
  checked: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
};

function CheckBox({ checked, onChange }: CheckBoxProps) {
  return <input type="checkbox" checked={checked} onChange={onChange} />;
}

export default CheckBox;
