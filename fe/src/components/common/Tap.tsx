import React from "react";
import { Link } from "react-router-dom";
import styled, { css, useTheme } from "styled-components";

import TagIcon from "components/Icons/Tag";
import MileStoneIcon from "components/Icons/MileStone";
import { FlexCenterBox, IconBox } from "styles/boxes";
import { tabStyle, linkStyle } from "constants/tabStyle";

interface TabProps {
  counts: {
    label: number;
    mileStone: number;
  };
}

function Tab({ counts }: TabProps) {
  const theme = useTheme();
  const { [tabStyle.font.fontColor]: iconColor } = theme.colors;

  return (
    <StyledTab as="ul">
      <TabItem>
        <Link to="/label" style={linkStyle}>
          <IconBox as="span" width={16} height={16}>
            <TagIcon color={iconColor} />
          </IconBox>
          레이블 ({counts.label})
        </Link>
      </TabItem>
      <TabItem>
        <Link to="/milestone" style={linkStyle}>
          <IconBox as="span" width={16} height={16}>
            <MileStoneIcon color={iconColor} />
          </IconBox>
          마일스톤 ({counts.mileStone})
        </Link>
      </TabItem>
    </StyledTab>
  );
}

const StyledTab = styled(FlexCenterBox)`
  ${({ theme: { colors } }) => {
    const { borderColor } = tabStyle;

    return css`
      display: inline-flex;
      border: 1px solid ${colors[borderColor]};
      border-radius: 11px;
      overflow: hidden;
    `;
  }}
`;

const TabItem = styled(FlexCenterBox)`
  ${({ theme: { colors, fontWeight, fontSize } }) => {
    const {
      tabItemSize: { width, height },
      bgColor,
      borderColor,
      font: { fontColor: ftColor, fontWeight: ftWeight, fontSize: ftSize },
    } = tabStyle;

    return css`
      width: ${width}px;
      height: ${height}px;
      background: ${colors[bgColor]};
      color: ${colors[ftColor]};
      font-weight: ${fontWeight[ftWeight]};
      font-size: ${fontSize[ftSize]};

      &:first-child {
        border-right: 1px solid ${colors[borderColor]};
      }
    `;
  }}
`;

export { Tab };
