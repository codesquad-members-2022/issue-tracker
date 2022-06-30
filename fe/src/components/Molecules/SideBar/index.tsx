/* eslint-disable array-callback-return */
/* eslint-disable no-shadow */
import React from 'react';

import Dropdown from 'components/Atoms/Dropdown';
import UserImage from 'components/Atoms/UserImage';
import Label from 'components/Atoms/Label';
import Milestone from 'components/Atoms/Milestone';

import { StyledSideBar, SideBarItem, Content, User } from 'components/Molecules/SideBar/index.styles';

import { AssignTypes, DropdownListTypes, LabelTypes, MilestoneTypes } from 'components/types';

type ContentListType = AssignTypes | MilestoneTypes | LabelTypes;

export interface SideBarListType {
  id: number;
  type: string;
  indicatorLabel: string;
  dropdownTitle?: string;
  dropdownList: AssignTypes[] | LabelTypes[] | MilestoneTypes[];
  contentList: ContentListType[];
  // eslint-disable-next-line no-unused-vars
  clickHandler?: (e: React.MouseEvent<HTMLInputElement>) => void;
  panelType?: 'checkbox' | 'radio';
}

export interface SideBarTypes {
  sideBarList: SideBarListType[];
  isEditer?: boolean;
}

const SideBar = ({ sideBarList, isEditer }: SideBarTypes): JSX.Element => {
  const isAssignTypes = (props: AssignTypes | LabelTypes | MilestoneTypes): props is AssignTypes => {
    return (props as AssignTypes).loginId !== undefined;
  };

  const isLabelTypes = (props: AssignTypes | LabelTypes | MilestoneTypes): props is LabelTypes => {
    return (props as LabelTypes).backgroundColor !== undefined;
  };

  const isMilestoneTypes = (props: AssignTypes | LabelTypes | MilestoneTypes): props is MilestoneTypes => {
    return (
      (props as MilestoneTypes).openIssueCount !== undefined && (props as MilestoneTypes).closedIssueCount !== undefined
    );
  };

  return (
    <StyledSideBar isEditer={isEditer}>
      {sideBarList.map(
        ({ id, type, indicatorLabel, dropdownTitle, dropdownList, contentList, clickHandler, panelType }) => (
          <div className="table-row" key={id}>
            <SideBarItem>
              <Dropdown
                type={type}
                dropdownTitle={dropdownTitle}
                dropdownList={dropdownList}
                indicatorLabel={indicatorLabel}
                indicatorStyle="SIDEBAR"
                panelType={panelType}
                clickHandler={clickHandler}
              />
              <Content type={type}>
                {contentList.map((props: ContentListType) => {
                  if (isAssignTypes(props))
                    return (
                      <User key={props.id}>
                        <UserImage profileImageUrl={props.profileImageUrl} loginId={props.loginId} imgSize="MEDIUM" />
                        <p>{props.loginId}</p>
                      </User>
                    );

                  if (isLabelTypes(props))
                    return (
                      <Label
                        key={props.id}
                        backgroundColor={props.backgroundColor}
                        title={props.title}
                        labelStyle="STANDARD"
                        titleColor={props.titleColor}
                      />
                    );
                  if (isMilestoneTypes(props))
                    return (
                      <div key={props.id}>
                        <Milestone openIssueCount={props.openIssueCount} closedIssueCount={props.closedIssueCount} />
                        <p>{props.title}</p>
                      </div>
                    );
                })}
              </Content>
            </SideBarItem>
          </div>
        ),
      )}
    </StyledSideBar>
  );
};

export default SideBar;
