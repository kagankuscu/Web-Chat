import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './login/component/register/register.component';


const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {path: 'register', component: RegisterComponent },
  {path: 'login', loadChildren: () => import('src/app/login/login.module').then(m => m.LoginModule) },
  {path: 'chat', loadChildren: () => import('src/app/chat/chat.module').then(m => m.ChatModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
