import Header from 'components/common/Header';
import ListContainer from 'components/common/ListContainer';
import SubNav from 'components/common/SubNav';

function MilestoneList() {
  return (
    <main className="wrap">
      <Header avatarUrl="null" />
      <SubNav location="MILESTONE" />
      <ListContainer headerItem="안녕하세요">
        <div>야호</div>
      </ListContainer>
    </main>
  );
}

export default MilestoneList;
