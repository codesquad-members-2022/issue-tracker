import MilestoneHeader from 'components/MilestoneList/MilestoneHeader';
import MilestoneItem from 'components/MilestoneList/MilestoneItem';
import Header from 'components/common/Header';
import ListContainer from 'components/common/ListContainer';
import SubNav from 'components/common/SubNav';

function MilestoneList() {
  return (
    <main className="wrap">
      <Header avatarUrl="null" />
      <SubNav location="MILESTONE" />
      <ListContainer headerItem={<MilestoneHeader />}>
        <MilestoneItem
          title="제목"
          dueDate="일정"
          desc="설명글"
          totalCount={5}
          doneCount={2}
          id="12313"
        />
      </ListContainer>
    </main>
  );
}

export default MilestoneList;
