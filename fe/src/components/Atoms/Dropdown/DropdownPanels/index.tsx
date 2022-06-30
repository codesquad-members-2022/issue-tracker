import React from 'react';

import checkOffCircle from 'assets/icons/checkOffCircle.svg';
import checkOnCircle from 'assets/icons/checkOnCircle.svg';
import {
  StyledDropdownPanels,
  DropdonwTitle,
  DropdownList,
} from 'components/Atoms/Dropdown/DropdownPanels/index.styles';
import UserImage from 'components/Atoms/UserImage';
import SmallLabel from 'components/Atoms/SmallLabel';

import { DropdownListTypes } from 'components/types';

export interface DropdownPanelsTypes {
  dropdownList: DropdownListTypes[];
  panelType?: 'checkbox' | 'radio';
  dropdownTitle?: string;
  type?: string;
  clickHandler?: (event: React.MouseEvent<HTMLInputElement>) => void;
}

const DropdownPanels = ({ panelType = 'checkbox', dropdownTitle = '필터 이름', ...props }: DropdownPanelsTypes) => {
  const { dropdownList, clickHandler, type } = props;

  return (
    <StyledDropdownPanels>
      <DropdonwTitle>{dropdownTitle}</DropdonwTitle>
      <DropdownList initIcon={checkOffCircle} activeIcon={checkOnCircle} {...props}>
        {dropdownList.map(({ id, loginId, title, backgroundColor, profileImageUrl }: DropdownListTypes) => (
          <li key={id}>
            <input
              key={`input-${id}`}
              type={panelType}
              name={dropdownTitle}
              id={`${dropdownTitle}-${loginId || title}`}
              data-type={type}
              data-id={id}
              onClick={clickHandler}
            />
            <label key={`label-${id}`} htmlFor={`${dropdownTitle}-${loginId || title}`}>
              {backgroundColor && <SmallLabel fill={backgroundColor} />}
              {profileImageUrl && <UserImage profileImageUrl={profileImageUrl} loginId={loginId!} imgSize="SMALL" />}
              <span>{loginId || title}</span>
            </label>
          </li>
        ))}
      </DropdownList>
    </StyledDropdownPanels>
  );
};

export default DropdownPanels;
