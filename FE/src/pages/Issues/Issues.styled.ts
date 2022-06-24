import styled, { css } from 'styled-components';
import { Link } from 'react-router-dom';
import { FilterStyle } from '../../common/Filter/FilterBar.styled';
import { CustomBtn } from '../../common/button.styled';

export const Container = styled.div`
  width: 1280px;
  margin: 0 auto;
`;

export const Wrapper = styled.div``;

export const Header = styled.div`
  display: flex;
  justify-content: space-between;
`;
export const Filter = styled.div`
  display: flex;
  width:600px
  height: 40px;
  justify-content: center;
  align-items: center;
  border: 1px solid ${({ theme: { colors } }) => colors.line};
  border-radius: 10px;
  div:first-child{
    width: 128px;
    justify-content: space-around;
  }
`;

export const FilterBar = styled.div`
  position: relative;
  padding: 5px;
  display: flex;
  justify-content: space-around;
  p {
    color: ${({ theme: { colors } }) => colors.label};
    font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  }
`;

export const SearchForm = styled.input`
  width: 472px;
  height: 40px;
  text-indent: 5px;
  border-radius: 0px 10px 10px 0px;
  background: ${({ theme: { colors } }) => colors.inputBackground};
`;

export const AddForm = styled.div`
  display: flex;
  align-items: center;
  div {
    margin-right: 5px;
  }
`;

export const LabelMilStoneContainer = styled.div`
  border: 1px solid #d9dbe9;
  display: flex;
  border-radius: 11px;
`;

export const LabelMilStoneWrapper = styled(Link)`
  width: 160px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-right: 1px solid #d9dbe9;
  p {
    color: ${({ theme: { colors } }) => colors.body};
    margin-left: 10px;
    font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  }
`;

export const AddIssueBtn = styled(CustomBtn)`
  margin-left: 20px;
  p {
    color: ${({ theme: { colors } }) => colors.offWhite};
    font-size: ${({ theme: { fontSizes } }) => fontSizes.xs};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
    margin-left: 5px;
  }
`;

export const Content = styled.div`
  border-top: 1px solid #ddd;
  border-left: 1px solid #ddd;
  border-right: 1px solid #ddd;
  border-radius: 5px;
  margin-top: 20px;
`;

export const IssueHeader = styled.div`
  width: 1280px;
  height: 64px;
  display: flex;
  background: #eee;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
  padding: 10px;
`;

export const Issue = styled.div`
  display: flex;
  align-items: center;
  gap: 20px;
`;

export const Check = styled.input``;

export const OpenedIssue = styled.div`
  display: flex;
  align-items: center;
  p {
    margin-left: 5px;
    font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
    color: ${({ theme: { colors } }) => colors.titleActive};
  }
`;
export const ClosedIssue = styled.div`
  display: flex;
  align-items: center;
  p {
    margin-left: 5px;
    font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
    color: ${({ theme: { colors } }) => colors.label};
  }
`;

export const Table = styled.div`
  display: flex;
  gap: 20px;
`;

export const TableList = styled.div`
  display: flex;
  p {
    margin-right: 5px;
    font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
    color: ${({ theme: { colors } }) => colors.label};
  }
`;

export const Issues = styled.div`
  display: flex;
  align-items: center;
  gap: 20px;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
  padding: 10px;
`;
export const IssuesLeft = styled.div``;

export const IssueTitleWrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 20px;
  margin-top: 10px;
  p {
    margin-left: 5px;
  }
`;

export const IssueContentWrapper = styled.div`
  margin-left: 40px;
  display: flex;
  align-items: center;
  gap: 20px;
  color: #999;
  margin: 20px 0 10px 40px;
  font-size: ${({ theme: { fontSizes } }) => fontSizes.sm};
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.normal};
  color: ${({ theme: { colors } }) => colors.label};
`;

export const IssueNumber = styled.div``;

export const TimeStamp = styled.div``;
export const MileStone = styled.div`
  display: flex;
  align-items: center;
  p {
    margin-left: 5px;
  }
`;
export const IssuesRight = styled.div`
  margin-right: 10px;
`;

export const IssueTitle = styled.div`
  display: flex;
  align-items: center;
  p {
    font-size: ${({ theme: { fontSizes } }) => fontSizes.md};
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
    color: ${({ theme: { colors } }) => colors.titleActive};
  }
`;
export const LabelTitle = styled.div`
  border-radius: 20px;
  background: blue;
  padding: 5px 16px;
  color: ${({ theme: { colors } }) => colors.offWhite};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.xs};
`;

export const IssuesContent = styled.div``;

export const DetailWrapper = styled.summary`
  display: flex;
  //width: 128px;
  display: flex;
  justify-content: space-around;
  ::marker {
    display: none;
    content: '';
  }
  p {
    margin-right: 10px;
  }
`;

export const DetailContent = styled.div<{ position: string }>`
  ${FilterStyle}
  position: absolute;
  // top: 35px;
  // left: 0;
  width: 240px;
  background: #fff;
  border: 1px solid #eee;
  border-radius: 16px;
  //padding: 5px;
  p {
    font-size: 15px;
    //padding: 8px;
  }
`;

export const IssueFilter = styled.p`
  background: #eee;
  padding: 10px;
  border-radius: 16px 16px 0px 0px;
`;

export const FilterTest = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  p {
    padding: 10px;
  }
`;
