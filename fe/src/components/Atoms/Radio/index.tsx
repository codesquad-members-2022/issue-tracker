/* eslint-disable jsx-a11y/label-has-associated-control */
import { Fragment } from 'react';
import { StyledRadioForm, StyledRadioOption } from 'components/Atoms/Radio/index.styles';

export interface TextOptionsTypes {
  id: number;
  label: string;
  handleOnClick: () => void;
}

interface RadioOptionTypes {
  title: string;
  textOptions: TextOptionsTypes[];
}

const RadioOption = ({ title, textOptions }: RadioOptionTypes) => {
  const Options = textOptions.map((option) => {
    const { id, label, handleOnClick } = option;
    const ID = `option${id}`;

    return (
      <Fragment key={ID}>
        <input type="radio" id={ID} name="option" onClick={handleOnClick} />
        <label htmlFor={ID}>
          <span>{label}</span>
        </label>
      </Fragment>
    );
  });

  return (
    <StyledRadioForm>
      <span>{title}</span>
      <StyledRadioOption>{Options}</StyledRadioOption>
    </StyledRadioForm>
  );
};

export default RadioOption;
