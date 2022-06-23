import { css } from 'styled-components';

const flexLayoutMixin = (
  direction = 'row',
  justify = 'flex-start',
  align = 'stretch',
) => css`
  display: flex;
  flex-direction: ${direction};
  justify-content: ${justify};
  align-items: ${align};
`;

const LAYOUT = {
  flexLayoutMixin,
};

const DISPLAY = css`
  font-size: 3.2rem;
  line-height: 48px;
`;
const DISPLAY_BOLD = css`
  font-weight: 700;
  font-size: 3.2rem;
  line-height: 48px;
`;
const TEXT_LARGE = css`
  font-size: 2.4rem;
  line-height: 40px;
`;
const LINK_LARGE = css`
  font-weight: 700;
  font-size: 2.4rem;
  line-height: 40px;
`;
const TEXT_MEDIUM = css`
  font-size: 1.8rem;
  line-height: 32px;
`;
const LINK_MEDIUM = css`
  font-weight: 700;
  font-size: 1.8rem;
  line-height: 32px;
`;
const TEXT_SMALL = css`
  font-size: 1.6rem;
  line-height: 28px;
`;
const LINK_SMALL = css`
  font-weight: 700;
  font-size: 1.6rem;
  line-height: 28px;
`;
const TEXT_X_SMALL = css`
  font-weight: 500;
  font-size: 1.2rem;
  line-height: 20px;
`;
const LINK_X_SMALL = css`
  font-weight: 700;
  font-size: 1.2rem;
  line-height: 20px;
`;
const LOGOTYPE_LARGE = css`
  font-family: 'Montserrat';
  font-style: italic;
  font-size: 5.6rem;
  line-height: 72px;
`;
const LOGOTYPE_REGULAR = css`
  font-family: 'Montserrat';
  font-style: italic;
  font-weight: 500;
  font-size: 3.2rem;
  line-height: 40px;
`;

const TYPOGRAPHY = {
  DISPLAY,
  DISPLAY_BOLD,
  TEXT_LARGE,
  LINK_LARGE,
  TEXT_MEDIUM,
  LINK_MEDIUM,
  TEXT_SMALL,
  LINK_SMALL,
  TEXT_X_SMALL,
  LINK_X_SMALL,
  LOGOTYPE_LARGE,
  LOGOTYPE_REGULAR,
};

const theme = {
  LAYOUT,
  TYPOGRAPHY,
};

export default theme;
