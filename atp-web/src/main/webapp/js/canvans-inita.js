/**
 * Created by Windows on 2016/3/15.
 */
/*默认显示*/
var showMe = function () {
    circleProgress({
        id: 'circle-progress-custom',
        progress: 0, // default: 100
        duration: 2000, // default: 1000
        color: '#fe9e10', // default: 'rgb(52, 145, 204)'
        bgColor: '#dbdbdb', // default: 'rgb(230, 230, 230)'
        textColor: '#fe9e10', // default: 'black'
        progressWidth: 0.15, // default: 0.25 (r)//圆环宽度
        fontScale: 0.3, // default: 0.4 (r)//字体大小
        toFixed: 0  // default: 0小数点后一位
    });
};

/*点击开始按钮初始化*/
var startMe = function () {
    circleProgress({
        id: 'circle-progress-custom',
        progress: 50, // default: 100
        duration: 2000, // default: 1000
        color: '#fe9e10', // default: 'rgb(52, 145, 204)'
        bgColor: '#dbdbdb', // default: 'rgb(230, 230, 230)'
        textColor: '#fe9e10', // default: 'black'
        progressWidth: 0.15, // default: 0.25 (r)//圆环宽度
        fontScale: 0.3, // default: 0.4 (r)//字体大小
        toFixed: 0  // default: 0小数点后一位
    });
};

var endMe = function () {
    circleProgress({
        id: 'circle-progress-custom',
        progress: 100, // default: 100
        duration: 2000, // default: 1000
        color: '#fe9e10', // default: 'rgb(52, 145, 204)'
        bgColor: '#dbdbdb', // default: 'rgb(230, 230, 230)'
        textColor: '#fe9e10', // default: 'black'
        progressWidth: 0.15, // default: 0.25 (r)//圆环宽度
        fontScale: 0.3, // default: 0.4 (r)//字体大小
        toFixed: 0  // default: 0小数点后一位
    });
};
