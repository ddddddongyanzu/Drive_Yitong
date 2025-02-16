// 基准路径  //路径仅为举例
// const baseurl ='http://8.140.1.230:8090'

const baseurl ='http://localhost:9090'
// const baseurl ='http://192.168.31.192:8091' 
 
//get请求
function get(url, data) {
  return new Promise((reslove, reject) => {
    wx.request({
      method: 'GET',
      url:baseurl + url,
      data,
      success: reslove,
      fail: reject
    })
  })
}
 
//post请求
function post(url, data) {
  return new Promise((reslove, reject) => {
    wx.request({
      method: 'POST',
      url, url:baseurl + url,
      data,
      success: reslove,
      fail: reject
    })
  })
}
 
function requestApi(url,callback){
  wx.showLoading({
    title: '加载中...',
  })
  wx.request({
    url: baseurl + url,
    data:{
  
    },
    header: { 'Content-Type': 'application/json' }, 
    timeout:500,
    success(res){
      wx.hideLoading({
        complete: (res) => {},
      })
      console.log(res.data);
      return typeof callback == "function" && callback(res.data);
    },
    fail(res){
      wx.hideLoading({
        complete: (res) => {console.log(res.errMsg)},
      })
      wx.showToast({
        title: '加载失败，请稍后重试！',
        icon:fail,
        mask: true,
        duration: 1500
      })
    }
  })
};

 //需要导出
module.exports = {
  get,
  post,
  baseurl,
  requestApi
}