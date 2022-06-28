import { CheckBoxType } from './CheckBox';
import { IssueType, SelectedIssueType } from './IssueList';

type selectedIssuesType = { [key: string]: boolean };

export type stateType = {
  headerCheckBox: 'initial' | 'active' | 'disable';
  selectedIssues: selectedIssuesType;
};

export type actionType = {
  type: 'INIT' | 'ALL_CHECK' | 'ALL_UNCHECK' | 'ITEM_CHECK' | 'ITEM_UNCHECK';
  payload: { id?: string; data?: IssueType[] };
};

function checkReducer(state: stateType, action: actionType): stateType {
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
        selectedIssues: initSelectedIssues(action.payload.data!)
      };
    }
    case 'ALL_CHECK': {
      const updatedSelectedIssues: selectedIssuesType = {
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
      const updatedSelectedIssues: selectedIssuesType = {
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
      const updatedSelectedIssues: selectedIssuesType = {
        ...state.selectedIssues
      };
      updatedSelectedIssues[action.payload.id!] = true;

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
      const updatedSelectedIssues: selectedIssuesType = {
        ...state.selectedIssues
      };
      updatedSelectedIssues[action.payload.id!] = false;

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
