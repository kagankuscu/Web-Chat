import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './shared/components/page-not-found/page-not-found.component';


const routes: Routes = [
  {path: 'auth', loadChildren: () => import('src/app/authentication/authentication.module').then(m => m.AuthenticationModule) },
  {path: 'chat', loadChildren: () => import('src/app/chat/chat.module').then(m => m.ChatModule) },

  {
    path: '',
    redirectTo: 'chat',
    pathMatch: 'full'
  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
