/* eslint-disable no-shadow */
/* eslint-disable no-unused-vars */
import styled, { css } from 'styled-components';

import {
  ButtonCustomStyleProps,
  StyledButtonProps,
  ButtonStyleType,
} from '@/components/Button/index';

const getButtonInnerColorStyle = (color: string) => `
  color: ${color};
  svg > * {
    stroke: ${color};
  }
`;

const standardColor = css`
  ${({ theme }) => getButtonInnerColorStyle(theme.colors.greyScale.offWhite)};
  background: ${({ theme }) => theme.colors.blueScale.blue};
  :enabled:hover {
    background: ${({ theme }) => theme.colors.blueScale.darkBlue};
  }
  :enabled:active {
    background: ${({ theme }) => theme.colors.blueScale.blue};
    border: 4px solid ${({ theme }) => theme.colors.blueScale.lightBlue};
  }
  :disabled {
    opacity: 50%;
  }
`;

const secondaryColor = css`
  ${({ theme }) => getButtonInnerColorStyle(theme.colors.blueScale.blue)};
  background: ${({ theme }) => theme.colors.greyScale.offWhite};
  border-color: ${({ theme }) => theme.colors.blueScale.blue};
  :enabled:hover {
    ${({ theme }) => getButtonInnerColorStyle(theme.colors.blueScale.darkBlue)};
    border-color: ${({ theme }) => theme.colors.blueScale.darkBlue};
  }
  :enabled:active {
    ${({ theme }) => getButtonInnerColorStyle(theme.colors.blueScale.blue)};
    border-color: ${({ theme }) => theme.colors.blueScale.lightBlue};
  }
  :disabled {
    opacity: 50%;
  }
`;

const textColor = css`
  ${({ theme }) => getButtonInnerColorStyle(theme.colors.greyScale.label)};
  :hover {
    ${({ theme }) => getButtonInnerColorStyle(theme.colors.greyScale.body)};
  }
  :enabled:active {
    ${({ theme }) =>
      getButtonInnerColorStyle(theme.colors.greyScale.titleActive)};
  }
  :disabled {
    opacity: 50%;
  }
`;

const largeStyle = css`
  width: 340px;
  height: 64px;
  border-radius: 20px;
  font-weight: 700;
  font-size: 18px;
  line-height: 32px;
  ${standardColor}
`;

const mediumStandardStyle = css`
  width: 240px;
  height: 56px;
  border-radius: 20px;
  font-weight: 700;
  font-size: 18px;
  line-height: 32px;
  ${standardColor}
`;

const smallStandardStyle = css`
  width: 120px;
  height: 40px;
  border-radius: 11px;
  font-weight: 700;
  font-size: 12px;
  line-height: 12px;
  ${standardColor}
`;

const smallSecondaryStyle = css`
  width: 120px;
  height: 40px;
  border: 2px solid;
  border-radius: 11px;
  border: 2px solid;
  ${secondaryColor}
`;

const mediumTextStyle = css`
  height: 32px;
  font-weight: 700;
  font-size: 16px;
  line-height: 28px;
  ${textColor}
`;

const smallTextStyle = css`
  width: 70px;
  height: 32px;
  font-weight: 700;
  font-size: 12px;
  line-height: 20px;
  ${textColor}
`;

const sortStyle = css`
  font-weight: 700;
  font-size: 16px;
  line-height: 28px;
  ${textColor}
`;

const largeLoginStyle = css`
  width: 340px;
  height: 64px;
  border-radius: 20px;
  font-weight: 700;
  font-size: 18px;
  line-height: 32px;
  ${({ theme }) => getButtonInnerColorStyle(theme.colors.greyScale.offWhite)};
  background: ${({ theme }) => theme.colors.greyScale.titleActive};
`;

const getButtonStyle = (style: ButtonStyleType) => {
  switch (style) {
    case 'large':
      return largeStyle;
    case 'mediumStandard':
      return mediumStandardStyle;
    case 'smallStandard':
      return smallStandardStyle;
    case 'smallSecondary':
      return smallSecondaryStyle;
    case 'mediumText':
      return mediumTextStyle;
    case 'smallText':
      return smallTextStyle;
    case 'sort':
      return sortStyle;
    case 'largeLogin':
      return largeLoginStyle;
    default:
      return '';
  }
};

const createCustomStyle = (props: ButtonCustomStyleProps) => css`
  ${props.width && { width: props.width }}
  ${props.height && { height: props.height }}
  ${props.color && getButtonInnerColorStyle(props.color)}
  ${props.background && { background: props.background }}
  ${props.border && { border: props.border }}
  ${props.borderRadius && { 'border-radius': props.borderRadius }}
  ${props.fontSize && { 'font-size': props.fontSize }}
  ${props.fontWeight && { 'font-weight': props.fontWeight }}
`;

export const Button = styled.button<StyledButtonProps>`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')};
  ${({ buttonStyle }) => getButtonStyle(buttonStyle)};
  ${({ ...props }) => createCustomStyle(props)}
  svg {
    margin-right: 6px;
  }
`;

export const Contents = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')};
`;
