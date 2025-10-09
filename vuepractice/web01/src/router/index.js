import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Modify from '../views/users/changeProfiles.vue'
import Passwordchangeconfirm from '@/components/passwordchangeconfirm.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    // redirect:'/home'
  },

    {
    path: '/login',
    component: () => import(/* webpackChunkName: "about" */ '../views/users/login.vue')
  },
  {
    path: '/user',
    component: () => import(/* webpackChunkName: "about" */ '../views/userList.vue')
  },

  {
    path: '/register',
    component: () => import(/* webpackChunkName: "about" */ '../views/users/register.vue')
  },
  {
    path: '/personal',
    component: () => import(/* webpackChunkName: "about" */ '../views/users/profs.vue')
  },
  {
    path: '/changeprofiles',
    component: () => import(/* webpackChunkName: "about" */ '../views/users/changeProfiles.vue')
  },
  {
    path: '/changepasswordconfirm',
    component: () => import(/* webpackChunkName: "about" */ '../views/users/changepasswordconfirm.vue')
  },
  {
    path: '/password/change/complete',
    name:'passwordchangecomplete',
    component:Passwordchangeconfirm
    // component: () => import(/* webpackChunkName: "about" */ '../views/users/changepasswordconfirm.vue')
  }

  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
