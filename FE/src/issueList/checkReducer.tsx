import { CheckBoxType } from './CheckBox';
import { IssueType, SelectedIssueType } from './IssueList';

export type IssueListCheckStateType = {
  headerCheckBox: CheckBoxType;
  selectedIssues: SelectedIssueType;
};

export type IssueListActionType = {
  type: 'INIT' | 'ALL_CHECK' | 'ALL_UNCHECK' | 'ITEM_CHECK' | 'ITEM_UNCHECK';
  payload?: { id?: string; data?: IssueType[] };
};

function checkReducer(
  state: IssueListCheckStateType,
  action: IssueListActionType
): IssueListCheckStateType {
  switch (action.type) {
    case 'INIT': {
      const initSelectedIssues = (issues: IssueType[]) => {
        const initialSelectedIssue: SelectedIssueType = {};

        issues.forEach(({ id }) => {
          initialSelectedIssue[id] = false;
        });

        return initialSelectedIssue;
      };

      return {
        headerCheckBox: 'initial',
        selectedIssues: initSelectedIssues(action.payload?.data!)
      };
    }
    case 'ALL_CHECK': {
      const updatedSelectedIssues: SelectedIssueType = {
        ...state.selectedIssues
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
        ...state.selectedIssues
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
        ...state.selectedIssues
      };
      updatedSelectedIssues[action.payload?.id!] = true;

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
        ...state.selectedIssues
      };
      updatedSelectedIssues[action.payload?.id!] = false;

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
