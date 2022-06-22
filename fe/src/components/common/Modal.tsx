import { Dispatch, SetStateAction } from 'react';
import styled from 'styled-components';
import Z_INDEX from 'styles/zIndex';

function Modal({
  children,
  setIsModalActive,
}: {
  children: React.ReactNode;
  setIsModalActive: Dispatch<SetStateAction<boolean>>;
}) {
  return (
    <>
      <Dim onClick={() => setIsModalActive(false)} />
      <Contents>{children}</Contents>
    </>
  );
}

const Dim = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: ${Z_INDEX.MODAL.DIM};
`;

const Contents = styled.div`
  position: relative;
  z-index: ${Z_INDEX.MODAL.CONTENTS};
  cursor: pointer;
`;

export default Modal;
