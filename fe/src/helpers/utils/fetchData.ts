import { AssignTypes } from 'components/types';
/* eslint-disable import/prefer-default-export */

interface SignUpFormTypes {
  id: number;
  login: string;
  name: string | null;
  email: string | null;
  avatar_url: string;
}

export interface jwtResponseTypes {
  accessToken: string;
  refreshToken: string;
}

export interface AuthTypes {
  isMember: boolean;
  authMemberResponse: SignUpFormTypes;
  memberResponse: AssignTypes;
  jwtResponse: jwtResponseTypes;
}
