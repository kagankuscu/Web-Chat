import { Action } from '@ngrx/store';

export enum TokenActionTypes {
  Add = '[Token Component] Add'
}

export class ActionEx implements Action {
  readonly type;
  payload: any;
}

export class TokenAdd implements ActionEx {
  readonly type = TokenActionTypes.Add;

  constructor(public payload: any) {
    console.log('TokenAdd');
  }
}
