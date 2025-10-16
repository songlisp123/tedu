import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainView from '@/views/MainView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
  {
    path: '/login',
    component: () => import(/* webpackChunkName: "about" */ '../views/loginview.vue')
  },

  {
    path: '/realhome',
    name: 'realHome',
    component:MainView,
    redirect:'/home',
    children:[
      {path: '/home',component: () => import(/* webpackChunkName: "about" */ '../views/home.vue')},
      {path: '/vehivle',component: () => import(/* webpackChunkName: "about" */ '../views/vehicle/vehicle.vue')},
      {path: '/geofence',component: () => import(/* webpackChunkName: "about" */ '../views/geofence/geofence.vue')},
      {path: '/geofencemap',component: () => import(/* webpackChunkName: "about" */ '../views/geofence/geofenceMap.vue')},
      {path: '/users',component: () => import(/* webpackChunkName: "about" */ '../views/user/userView.vue')},
      {path: '/change/password/confirm',component: () => import(/* webpackChunkName: "about" */ '../views/user/changepasswordConfirm.vue')},
      {path: '/change/password/complete',component: () => import(/* webpackChunkName: "about" */ '../views/user/changepasswordcomplete.vue')},
    ]
  },
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
