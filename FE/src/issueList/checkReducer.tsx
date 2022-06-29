import { CheckBoxType } from './CheckBox';
import { SelectedIssueType } from './IssueList';

export type IssueListCheckStateType = {
  headerCheckBox: CheckBoxType;
  selectedIssues: SelectedIssueType;
};

export type IssueListActionType = {
  type: 'INIT' | 'ALL_CHECK' | 'ALL_UNCHECK' | 'ITEM_CHECK' | 'ITEM_UNCHECK';
  payload?: { id?: string; initialSelectedIssues?: SelectedIssueType };
};

function checkReducer(
  state: IssueListCheckStateType,
  action: IssueListActionType
): IssueListCheckStateType {
  const { selectedIssues } = state;
  const { type, payload } = action;

  switch (type) {
    case 'INIT': {
      return {
        headerCheckBox: 'initial',
        selectedIssues: payload?.initialSelectedIssues!
      };
    }
    case 'ALL_CHECK': {
      const updatedSelectedIssues: SelectedIssueType = {
        ...selectedIssues
      };
      Object.keys(updatedSelectedIssues).forEach((id) => {
        updatedSelectedIssues[id] = true;
      });
      return {
        headerCheckBox: 'active',
        selectedIssues: updatedSelectedIssues
      };
    }
    case 'ALL_UNCHECK': {
      const updatedSelectedIssues: SelectedIssueType = {
        ...selectedIssues
      };
      Object.keys(updatedSelectedIssues).forEach((id) => {
        updatedSelectedIssues[id] = false;
      });
      return {
        headerCheckBox: 'initial',
        selectedIssues: updatedSelectedIssues
      };
    }
    case 'ITEM_CHECK': {
      const updatedSelectedIssues: SelectedIssueType = {
        ...selectedIssues
      };
      updatedSelectedIssues[payload?.id!] = true;

      let headerCheckBox: CheckBoxType;
      if (
        Object.values(updatedSelectedIssues).every((isChecked) => isChecked)
      ) {
        headerCheckBox = 'active';
      } else {
        headerCheckBox = 'disable';
      }

      return {
        headerCheckBox,
        selectedIssues: updatedSelectedIssues
      };
    }
    case 'ITEM_UNCHECK': {
      const updatedSelectedIssues: SelectedIssueType = {
        ...selectedIssues
      };
      updatedSelectedIssues[payload?.id!] = false;

      let headerCheckBox: CheckBoxType;
      if (
        Object.values(updatedSelectedIssues).every((isChecked) => !isChecked)
      ) {
        headerCheckBox = 'initial';
      } else {
        headerCheckBox = 'disable';
      }

      return {
        headerCheckBox,
        selectedIssues: updatedSelectedIssues
      };
    }
    default:
      return state;
  }
}

export default checkReducer;
