import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/transactions',
    name: '',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "transactions" */ '../views/Transactions.vue')
  },
  {
    path: '/settings',
    name: '',
    component: () => import( '../views/Settings.vue')
  },
  {
    path: '/bank-accounts',
    name: '',
    component: () => import( '../views/BankAccounts.vue')
  }

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
