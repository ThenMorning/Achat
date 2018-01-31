import VueRouter from 'vue-router';
import Vue from 'vue';

Vue.use(VueRouter);

import Login from 'src/views/login/login.vue';
import Register from 'src/views/register/register.vue';
import Main from 'src/views/main/main.vue';


const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },
];

let router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  if(to.name == 'Login'){
    next();
  }
  else if(to.name == 'Main' && from.name == 'Login'){
    next();
  }
  else if(to.name == 'Register' && from.name == 'Login'){
    next();
  }
  else{
    next('/');
  }
})

export default router;
