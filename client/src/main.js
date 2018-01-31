// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//import 'src/assets/font/iconfont.css'

import Vue from 'vue';
import App from './App';
import ElementUI from 'element-ui'
import './element-variables.scss'
import router from './router/router';

Vue.use(ElementUI)
Vue.config.devtools = true;

let vm = new Vue({
  router,
  render: h => h(App),//render函数是渲染一个视图，然后提供给el挂载   vue2.0的ES6写法   vue1.0为  components: { App }
}).$mount('#app');



