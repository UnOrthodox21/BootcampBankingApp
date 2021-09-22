import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component:() => import( '../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component:() => import( '../views/Register.vue')
  },
  {
    path: '/transactions',
    name: 'Transactions',
    component: () => import('../views/Transactions.vue')
  },
  {
    path: '/settings',
    name: 'Settings',
    component: () => import( '../views/Settings.vue')
  },
  {
    path: '/bank-accounts',
    name: 'BankAccounts',
    component: () => import( '../views/BankAccounts.vue')
  },
  {
    path: '/transactions-history',
    name: 'TransactionsHistory',
    component: () => import( '../views/TransactionsHistory.vue')
  }

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
