import { CheckBoxType } from '@issueListTable/CheckBox';
import { SelectedIssueType } from '@issueListTable/IssueList';

export type IssueListCheckStateType = {
  headerCheckBox: CheckBoxType;
  selectedIssues: SelectedIssueType;
};

export type IssueListActionType =
  | { type: 'INIT'; payload: { initialSelectedIssues: SelectedIssueType } }
  | { type: 'ALL_CHECK' }
  | { type: 'ALL_UNCHECK' }
  | { type: 'ITEM_CHECK'; payload: { id: string } }
  | { type: 'ITEM_UNCHECK'; payload: { id: string } };

function checkReducer(
  state: IssueListCheckStateType,
  action: IssueListActionType
): IssueListCheckStateType {
  const { selectedIssues } = state;

  switch (action.type) {
    case 'INIT': {
      return {
        headerCheckBox: 'initial',
        selectedIssues: action.payload.initialSelectedIssues!
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
      updatedSelectedIssues[action.payload.id] = true;

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
      updatedSelectedIssues[action.payload.id] = false;

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
