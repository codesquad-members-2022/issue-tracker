import MilestoneHeader from 'components/MilestoneList/MilestoneHeader';
import Header from 'components/common/Header';
import ListContainer from 'components/common/ListContainer';
import SubNav from 'components/common/SubNav';

function MilestoneList() {
  return (
    <main className="wrap">
      <Header avatarUrl="null" />
      <SubNav location="MILESTONE" />
      <ListContainer headerItem={<MilestoneHeader />}>
        <div>야호</div>
      </ListContainer>
    </main>
  );
}

export default MilestoneList;
