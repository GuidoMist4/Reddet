import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
<<<<<<< Updated upstream

const routes: Routes = [];
=======
import { SignupComponent } from './auth/signup/signup.component';

const routes: Routes = [{path:'sign-up',component:SignupComponent}];
>>>>>>> Stashed changes

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
