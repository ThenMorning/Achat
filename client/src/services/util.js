/*脏话判断*/
function haveDirtyWords(str) {
  // let rules = /[((傻|妈|操|草|煞|日|狗|泥马|尼玛|你妈|你吗|他妈|他吗)(泥马|尼玛|尼马|逼|了个逼|比|笔|你妈|勒戈壁))|(尼马|尼玛|屌|鸡把|鸡巴|几把|((?<![a-z0-9A-Z])(sb|SB|Sb|sB|fuck|Fuck|FUCK)(?![a-z0-9A-Z])))]/g
  let rules = /((傻|妈|操|草|煞|日|狗|泥马|尼玛|你妈|你吗|他妈|他吗)(泥马|尼玛|尼马|逼|了个逼|比|笔|你妈|勒戈壁))|(尼马|尼玛|屌|鸡把|鸡巴|几把|fuck|Fuck|FUCK|SB|Sb|sb|sB)/g
  return rules.test(str);
}

/*特殊字符判断*/
function haveSpecialChar(str) {
  let regEn = /[ `~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
    regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;

  return regEn.test(str) || regCn.test(str);
}

/*删除特殊字符*/
function deleteSpecialChar(str) {
  let regEn = /[ `~!@#$%^&*()_+<>?:"{},.\/;'[\]·！#￥（——）：；“”‘、，|《。》？、【】[\]]/gim;
  return str.replace(regEn, '')
}

/*请求封装*/
import axios from 'axios';

// //返回状态判断(添加响应拦截器)
// axios.interceptors.response.use((res) => {
//   //对响应数据做些事
//   if (!res.data.success) {
//     return Promise.reject(res);
//   }
//   return res;
// }, (error) => {
//   alert("网络异常");
//   return Promise.reject(error);
// });


function getRequest(options) {
  return new Promise((resolve, reject) => {
    axios({
      method: 'get',
      url: options.url,
      data: options.para
    }).then(response => {
      resolve(response.data);
    }, err => {
      reject(err);
    })
      .catch((error) => {
        reject(error)
      })
  })
}

function postRequest(options) {
  return new Promise((resolve, reject) => {
    axios({
      method: 'post',
      url: options.url,
      data: options.para
    }).then(response => {
      resolve(response.data);
    }, err => {
      reject(err);
    })
      .catch((error) => {
        reject(error)
      })
  })
}


export {haveDirtyWords, haveSpecialChar, deleteSpecialChar, getRequest,postRequest};
