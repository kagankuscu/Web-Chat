import { UserModel } from 'src/app/user/model/user.model';

export interface AuthDataModel {
  user: UserModel;
  token: string;
}
