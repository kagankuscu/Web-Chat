import { ActionEx, TokenActionTypes } from './token.actions';

export const initialState = '';

export function TokenReducer(state = initialState, actions: ActionEx) {
  switch (actions.type) {
    case TokenActionTypes.Add:
      console.log('reduceer');
      console.log('state ', state);
      console.log('actions.payload ', actions.payload);
      return actions.payload;
      break;

    default:
      break;
  }
}
