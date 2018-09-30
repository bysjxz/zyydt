var recipelItems = new Array();     //治疗信息
var names = new Array();    //穴位名称
var recipelItem = new Array();      //单个穴位信息
var lastName = "";   //上一个穴位
var TimeFn = null;

/**
 * 点击下一穴位
 * 保存本穴位的治疗信息到recipelItems数组中
 * 并将该穴位添加到处方中
 */
function nextXw(){
	var items = new Array();
	var name = $("#name").get(0).innerText;
	if($.inArray(name,names)==-1){
		names.push(name)
		$('.usage .yf_content_select').each(function(i, item) {
			items[i] = $(this).text();
		});
		var recipelItemJson = '{name:'+name+',time:'+items[0]
            +',zzMethod:'+items[1]+',freq:'+items[2]+',waveform:'+items[3]+',bx:'+items[4]+'}';
		recipelItem.push(recipelItemJson);
		//如果对穴位进行了设置，就将其添加到处方中
		var cfVal = $('#cf').val()
		if(cfVal==''){
			$('#cf').val(name)
		}else{
			$('#cf').val(cfVal+'、'+name)
		}
	}else{
		alert('处方穴位<<'+name+'>>重复')
	}
	
	alert('目前添加的穴位项： '+recipelItems);
}

/**
 * 应用到所有--------仲永键
 */
function applyAll() {
    if ($('#applyAll').text() == "应用到所有") {
        if (lastName == "") {
            alert("你未选择或者添加任何穴位,请选择");
        } else {
            /**
             * 1、获取填写的所有治疗信息，保存到数组中
             * 2、遍历所有的元素，然后将保存的信息赋值给未保存的穴位
             * 3、将全部信息保存到另一数组中
             */
            //先清空保存穴位的数组
            names.splice(0,names.length);
            //清空保存穴位治疗信息的数组
            recipelItems.splice(0,recipelItems.length);
            //获取填写的所有治疗信息，保存到数组中
            var treatmentItem = new Array();
            $('.yf_content_select').each(function() {
                treatmentItem.push($(this).text());
            });
            var recipelItemJson = {"time": treatmentItem[0],
                "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                "waveform":treatmentItem[3],"bx":treatmentItem[4]};
            recipelItem.push(recipelItemJson);
            //存储所有的穴位名称
            var items = new Array();
            /*   $('.xw_content').each(function () {
                   items.push($(this).text());
               });*/
            $('.xw_content_select').each(function () {
                items.push($(this).text());
            });

            console.log(items);
            //遍历所有的穴位名称，为其赋值
            for(j = 0; j < items.length; j++) {
                recipelItemJson = {"name":items[j],"time":treatmentItem[0],
                    "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                    "waveform":treatmentItem[3],"bx":treatmentItem[3]};
                recipelItems.push(recipelItemJson);
                names.push(items[j]);
            }
            lastName = "应用成功";
            alert("应用成功");
            $('#applyAll').text("取消应用");
        }
    } else {
        alert("取消应用成功");
        $('#applyAll').text("应用到所有");
    }
}
/**
 * 发送处方
 *  处方信息通过数组的形式发送
 */
function sendRecipel() {
    // alert("sendRecipel");
    if (names.length== 0 && lastName == "") {
        alert("你还未填写任何治疗信息，请填写");
        $("#bj").css('display','none')//隐
        $("#todo-task-modal").css('display','none')//隐
    } else {
        var url = contextPath + "/treatment/cfyx";
        $("#bj").css('display','block')//显
        $("#todo-task-modal").css('display','block')//显

        if (lastName != "应用成功" && $.inArray(lastName,names) == -1) {
            var treatmentItem = new Array();
            $('.yf_content_select').each(function() {
                treatmentItem.push($(this).text());
            });
            var recipelItemJson = {"name":lastName,"time": treatmentItem[0],
                "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                "waveform":treatmentItem[3],"bx":treatmentItem[4]};
            recipelItems.push(recipelItemJson);
            names.push(lastName);
            lastName = "";
            //restoreDefault();   //恢复用法默认
        }
            
 /*       //将选中的所有穴位都暂时保存起来
        $('.xw_content_select').each(function() {
            if ($.inArray($(this).text(),names) == -1) {
                names.push($(this).text());
            }
        });*/


        $.ajax({
            type: 'post',
            url: url,
            data:{
                recipelItems: JSON.stringify(recipelItems)
            },
            success: function(msg) {
                if (msg.status == "TS") {
                    alert(msg.getContent());
                }
            }
        });
    }
}

/**
 * 下拉框选中穴位
 * 使用easyUI的combogrid自动检索，点选需要的值添加用穴
 */
function selectXw(){
    var xw = $('#serachdiv').val();

    if (lastName != "") {
        //如果不存在信息，则为添加信息
        if ($.inArray(lastName,names) == -1 && lastName != "应用成功") {
            var treatmentItem = new Array();
            $('.yf_content_select').each(function() {
                treatmentItem.push($(this).text());
            });
            var recipelItemJson = {"name":lastName,"time": treatmentItem[0],
                "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                "waveform":treatmentItem[3],"bx":treatmentItem[4]};
            recipelItems.push(recipelItemJson);
            names.push(lastName);
            lastName = xw;
            restoreDefault();   //恢复用法默认
        }
        //如果存在治疗信息，则为回显治疗信息
        if ($.inArray(xw,names) != -1) {
            showAgain();
        }
    } else {
        //未记录到任何穴位，就保存当前穴位
        lastName = xw;
        restoreDefault();   //恢复用法默认
    }


    if ($(".xws").innerText != "") {
        var xwBtn = '<div class="xw_content_select" ondblclick="del(this)" onclick="xwclick(this)">'+xw+'</div>';
        $('#xws').append(xwBtn);
    } else {
        var arr= new Array();
        $('#xws div').each(function(index,item){
            arr[index] = $(this).text();
            $(this).attr("class","xw_content")
        });
        name =  $('#serachdiv').combogrid('getText');
        var i = $.inArray(xw, arr);
        if(i==-1){
            //$("#name").text(xw);
            var xwBtn = '<div class="xw_content_select" ondblclick="del(this)" onclick="xwclick(this)">'+xw+'</div>';
            $('#xws').append(xwBtn);
        }
    }
    /**
     * 下拉框选中数据后清空输入框信息
     */
    var _temp_SelectRow = $("#serachdiv").combogrid("grid").datagrid('getSelected');
    var _var_Combgrid_SelectValue = $("#serachdiv").combogrid('getValue');
    if (!_temp_SelectRow || _var_Combgrid_SelectValue != _temp_SelectRow.guid) {//没有选择或者选项不相等时清除内容
        $("#serachdiv").combogrid('setValue', '');
    }
}

/**
 * 用法点击变色
 * 先找到用法下面的第一个结点，保存起来
 * 再找到该用法的所有下属结点，并将每一个结点的class属性设为默认未选中
 * 然后将第一个结点设置为选中状态
 */
$(".usage div").click(function() {
	//var text = $(this).get(0).innerText;
	//var name = $("#name").get(0).innerText;
	//找同级兄弟姊妹
	var siblings = $(this).siblings();
	$.each(siblings, function(index, item) {
		$(item).attr("class", "yf_content");
	});
	var boolean = false;
	$(this).attr("class", "yf_content_select");
});

// 推荐穴位事件
function tuijian(me){
	 //后期可以通过id 查询获取数据
	 var xw = JSON.parse($(me).attr('data'));
	 // 判断重复
	 var boolean = true;
    $.each(items,function(index,item){
   	 console.log(item.name+"----"+xw.name);
 	   if(item.name==xw.name&&item.ctype==xw.ctype){
 		   boolean = false;
 	   }
    });
    if(boolean){
      items.push(new xwcontent(xw.ctype,xw.name,xw.time,xw.zz_method,xw.freq,xw.waveform,xw.bx,xw.jl,xw.type,xw.gj,xw.other_gf,xw.cxxh,xw.yj,xw.jz,xw.power));
	   addxw("xwtj");
	   if(xw.ctype==$(".menu_select").get(0).innerText){    		   
		   yfRender(xw.name);
	   }
    }else{
      layer.msg("<<"+xw.ctype+">>中<<"+xw.name+">>已重复");
    }
}
 

//应用到所有
function allyfset(){
  var name = $("#name").get(0).innerText;
  var xwentity;
  $.each(items,function(index,item){
    if(item.name == name&&item.ctype ==$(".menu_select").get(0).innerText){
       xwentity = item;
    }
  });
  $.each(items,function(index,item){
	   if(item.ctype ==$(".menu_select").get(0).innerText){
		   item.time=xwentity.time;
		   item.zz_method=xwentity.zz_method;
		   item.freq=xwentity.freq;
		   item.waveform=xwentity.waveform;
		   item.bx=xwentity.bx;
           //item.strength=xwentity.strength;
		   /*item.jd=xwentity.jd;
		   item.sd=xwentity.sd;*/
		   item.jl=xwentity.jl;  
		   item.type=xwentity.type;  
		   item.gj=xwentity.gj;  
		   item.other_gf=xwentity.other_gf;  
		   item.cxxh=xwentity.cxxh;  
		   item.yj=xwentity.yj;  
		   item.jz=xwentity.jz;  
		   item.power=xwentity.power;  
	   }
  });
  layer.msg("成功！");
}
//用法复位
function reset(){
	var xwname=$(".xw_select").get(0).innerText;
   var op =$(".usage");
   $.each(op,function(index,item){
     $.each($(item).children(),function(i,p){
        $(p).attr("class","yf_content");
     });
     $(item).children().first().attr("class","yf_content_select");
   });
   $.each(items,function(index,item){
   	if ($(".menu_select").get(0).innerText=="针刺"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.time="30min";
   			item.zz_method="普通";
   			item.freq="疏密波";
   			item.waveform="20Hz";
            item.bx="补";
   			//item.strength="1";
   			//item.jd="浅";
   			//item.sd="平刺";
   		}
   	}
   	else if($(".menu_select").get(0).innerText=="艾灸"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.time="15min";
   			item.type="化脓灸";
   			item.bx="补";
   			item.jl="大";
   		}     		
   	}
   	else if($(".menu_select").get(0).innerText=="拔罐"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.gj="竹罐";
   			item.zz_method="闪火法";
   			item.time="10min";
   			item.other_gf="针罐";
   		}     		
   	}
   	else if($(".menu_select").get(0).innerText=="埋线"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.cxxh="I";;
   		}     		
   	}
   	else if($(".menu_select").get(0).innerText=="小针刀"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.yj="小号";;
   		}     		
   	}
   	else if($(".menu_select").get(0).innerText=="刮痧"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.yj="I";
   			item.jz="I";
   			item.bx="补";
   			item.time="15min";
   		}     		
   	}
   	else if($(".menu_select").get(0).innerText=="牵引"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.time="15min";
   			item.power="50N";
   		}     		
   	}
   	else if($(".menu_select").get(0).innerText=="其它"){
   		if(item.ctype==$(".menu_select").get(0).innerText&&xwname == item.name){
   			item.time="30min";
   			item.zz_method="普通";
   			item.freq="疏密波";
   			item.waveform="20Hz";
            item.bx="补";
   			//item.strength="1";
   			//item.jd="浅";
   			//item.sd="平刺";
   		}     		
   	}
   });
  	console.log(items)
}

/**
 * 双击删除穴位标签-------------张登朝
 * 	1、要删除显示框中文本框
 * 	2、删除处方框中对应文字
 * 	3、删除治疗选择框的显示
 */
 function del(me){
    clearTimeout(TimeFn);
    var textVlu = $(me).text();
    $(me).remove();
    if (textVlu == lastName) {
        lastName = "";
    }
    for (var i = 0;i < recipelItems.length;i++) {
        if (textVlu == recipelItems[i].name) {
            recipelItems.splice(i,1);
            break;
        }
    }
    for (var i = 0;i < names.length;i++) {
        if (textVlu == names[i]) {
            names.splice(i,1);
            break;
        }
    }
    restoreDefault();   //恢复用法默认
   }

/**
 * 穴位单击事件
 *      1、改变穴位的选中状态
 *      2、判断是否存在治疗信息
 *      3、存在，点击单个穴位时，渲染治疗信息界面
 * @param me
 */
 function xwclick(me){
    clearTimeout(TimeFn);
    TimeFn = setTimeout(function(){

    var name = $(me).get(0).innerText;
    if (lastName != "") {
        //如果不存在信息，则为添加信息
        if ($.inArray(lastName,names) == -1 && lastName != "应用成功") {
            var treatmentItem = new Array();
            $('.yf_content_select').each(function() {
                treatmentItem.push($(this).text());
            });
            var recipelItemJson = {"name":lastName,"time": treatmentItem[0],
                "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                "waveform":treatmentItem[3],"bx":treatmentItem[4]};
            recipelItems.push(recipelItemJson);
            names.push(lastName);
            lastName = name;
            restoreDefault();   //恢复用法默认
        }
        //如果存在治疗信息，则为回显治疗信息
        if ($.inArray(name,names) != -1) {
            showAgain();
        }
    } else {
        //未记录到任何穴位，就保存当前穴位
        lastName = name;
        restoreDefault();   //恢复用法默认
    }

        //改变穴位的选中状态
        //siblings获取同级元素
        /*var siblings = $(me).siblings();
        $.each(siblings,function(index,item){
            $(item).attr("class","xw_content");
        });*/
     $(me).attr("class","xw_content_select");
    },250);
 }

/**
 * 单击保存穴位治疗信息
 * @param name
 */
/*function clickSave(name) {
    //先判断治疗信息数组中是否有对应的值
     if (recipelItems != "") {
         if (lastName != "") {
             //如果不存在信息，则为添加信息
             if ($.inArray(lastName,names) == -1 && lastName != "应用成功") {
                 var treatmentItem = new Array();
                 $('.yf_content_select').each(function() {
                     treatmentItem.push($(this).text());
                 });
                 var recipelItemJson = {"name":lastName,"time": treatmentItem[0],
                     "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                     "waveform":treatmentItem[3],"bx":treatmentItem[4]};
                 recipelItems.push(recipelItemJson);
                 names.push(lastName);
                 lastName = name;
             } /!*else if ($.inArray(name,names) != -1) {    //如果存在信息，则为回显治疗信息
                        showAgain();
                    }*!/
             //如果存在治疗信息，则为回显治疗信息
             if ($.inArray(name,names) != -1) {
                 showAgain();
             }
         }
     } else {
         if (lastName != "") {
             var treatmentItem = new Array();
             $('.yf_content_select').each(function() {
                 treatmentItem.push($(this).text());
             });
             var recipelItemJson = {"name":lastName,"time": treatmentItem[0],
                 "zzMethod":treatmentItem[1],"freq":treatmentItem[2],
                 "waveform":treatmentItem[3],"bx":treatmentItem[4]};
             recipelItems.push(recipelItemJson);
             names.push(lastName);
             lastName = name;
         }
         lastName = name;
     }
 }*/

/**
 * 穴位数据回显
 */
function showAgain() {
     alert("该穴位已填写");
     // display();
     $("#time .yf_content").each(function () {
         //如果当前选中的不是对应的治疗方案中的，则改变其选中
         if ($(this).text() == recipelItems[i].time) {
             var times = $(this).siblings();
             $.each(times,function(index,item){
                 $(item).attr("class","yf_content");
             });
             $(this).attr("class","yf_content_select");
         }
     });
     $("#zz_method .yf_content").each(function () {
         //如果当前选中的不是对应的治疗方案中的，则改变其选中
         if ($(this).text() == recipelItems[i].zzMethod) {
             var times = $(this).siblings();
             $.each(times,function(index,item){
                 $(item).attr("class","yf_content");
             });
             $(this).attr("class","yf_content_select");
         }
     });
     $("#freq .yf_content").each(function () {
         //如果当前选中的不是对应的治疗方案中的，则改变其选中
         if ($(this).text() == recipelItems[i].freq) {
             var times = $(this).siblings();
             $.each(times,function(index,item){
                 $(item).attr("class","yf_content");
             });
             $(this).attr("class","yf_content_select");
         }
     });
     $("#waveform .yf_content").each(function () {
         //如果当前选中的不是对应的治疗方案中的，则改变其选中
         if ($(this).text() == recipelItems[i].waveform) {
             var times = $(this).siblings();
             $.each(times,function(index,item){
                 $(item).attr("class","yf_content");
             });
             $(this).attr("class","yf_content_select");
         }
     });
     $("#bx .yf_content").each(function () {
         //如果当前选中的不是对应的治疗方案中的，则改变其选中
         if ($(this).text() == recipelItems[i].bx) {
             var times = $(this).siblings();
             $.each(times,function(index,item){
                 $(item).attr("class","yf_content");
             });
             $(this).attr("class","yf_content_select");
         }
     });
 }

  // 渲染被选中的穴位 (menuclick菜单点击事件 ----- xwtj 穴位推荐---- delxw删除穴位---addxw添加穴位 )
  function addxw(type){
     var textdatashtml = "";
     for (var index in items){
   	  if(items[index].ctype ==$(".menu_select").get(0).innerText){        		  
   		 textdatashtml +="<div  id='t"+index+"' class='xw' ondblclick='del(this)' onclick='xwclick(this)'>"+items[index].name+"</div>";
   	  }
     }
     if(textdatashtml==""&&items[0]!=undefined&&type=="xwtj"){
   	  layer.msg("已经添加到"+items[0].ctype);
     }
     $('#button_xuewei').html(textdatashtml);
       //有数据渲染第一个穴位
     if(type == "menuclick"&&textdatashtml!=""){
   	  var xwname = $('#button_xuewei').children();
   	  yfRender(xwname.get(0).innerText);
     }
     if(type=="delxw"){
   	  if(textdatashtml==""){      		  
   		  //去掉顶部菜单项目样式
   		  var css = $(".menu_select").attr('class');
   		  $(".menu_select").attr("class",css.replace('multiborder',''));
   	  }else{
   		  var xwname = $('#button_xuewei').children();
       	  yfRender(xwname.get(xwname.length -1).innerText);
   	  }
     }
  }
  $('#serachdiv').combogrid({
           prompt:'请输入关键字后自动搜索',  
           panelWidth:'436px',
           idField:'name',
           textField:'name',
           fitColumns:true,
           url: 'serach',
           mode:'remote',
           striped: true,
           columns:[[
               {field:'id',title:'编号',width:40},
               {field:'name',title:'名称',width:40},
               {field:'pinyin',title:'拼音',width:20}                   
           ]],
           onClickRow:function(){
           	    selectXw();
             }
       });
      $('#serachdiv').combogrid('textbox').keyup(function(event) {
       if(event.keyCode!='13'){
           name=  $('#serachdiv').combogrid('getText');
        }
        var rowSelected = $("#serachdiv").combogrid("grid").datagrid("getSelected"); 
        if (event.keyCode == '13'&&rowSelected==null) {
            $('#serachdiv').combogrid('setValue',diseasename); 
           $('#serachdiv').combogrid("hidePanel");
              
        }else if(event.keyCode == '13'&&rowSelected!=null){
          console.log(rowSelected);
          selectXw();
     }
     });
     $('#serachdiv').combogrid('textbox').focus(function() {
           name=  $('#serachdiv').combogrid('getText');
     });
     //穴位判断重复方法
     function check(name){
       
     }
     //获取穴位单个对象
     function getxwContent(name){
        var xwcontent ="";
       $.each(items,function(index,item){
        if(item.name == name&&item.ctype==$(".menu_select").get(0).innerText){
         xwcontent =item;
        }
       });
       return xwcontent;
     }
     
     //穴位用法渲染
     function yfRender(name){
      $("#name").get(0).innerText = name;
      clearyf();
      clearxw();
      var xwcontent =getxwContent(name);
       var time = $("#time").children();
       var xwnames = $("#button_xuewei").children();
       var zz_method = $("#zz_method").children();
       var freq = $("#freq").children();
       var waveform = $("#waveform").children();
       var  bx = $("#bx").children();
       //var  strength = $("#strength").children();
       //var  jd = $("#jd").children();
       //var  sd = $("#sd").children();
       var  jl = $("#jl").children();
       var  jz = $("#jz").children();
       var type = $("#type").children();
       var gj = $("#gj").children();
       var other_gf = $("#other_gf").children();
       var cxxh = $("#cxxh").children();
       var yj = $("#yj").children();
       var jz = $("#jz").children();
       var power = $("#power").children();
       $.each(xwnames,function(index,item){
       	if($(item).get(0).innerText ==name ){
               $(item).attr("class","xw_select");
             }  
       });
       $.each(time,function(index,item){
         if($(item).get(0).innerText ==xwcontent.time ){
           $(item).attr("class","yf_content_select");
         }  
       });
       $.each(zz_method,function(index,item){
         if($(item).get(0).innerText ==xwcontent.zz_method ){
           $(item).attr("class","yf_content_select");
         }  
       });
        $.each(freq,function(index,item){
         if($(item).get(0).innerText ==xwcontent.freq ){
           $(item).attr("class","yf_content_select");
         }  
       });
         $.each(waveform,function(index,item){
             if($(item).get(0).innerText ==xwcontent.waveform ){
                 $(item).attr("class","yf_content_select");
             }
         });
        $.each(bx,function(index,item){
         if($(item).get(0).innerText ==xwcontent.bx ){
           $(item).attr("class","yf_content_select");
         }  
       });
        $.each(jl,function(index,item){
            if($(item).get(0).innerText ==xwcontent.jl ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(sd,function(index,item){
            if($(item).get(0).innerText ==xwcontent.sd ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(type,function(index,item){
            if($(item).get(0).innerText ==xwcontent.type ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(gj,function(index,item){
            if($(item).get(0).innerText ==xwcontent.gj ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(other_gf,function(index,item){
            if($(item).get(0).innerText ==xwcontent.other_gf ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(cxxh,function(index,item){
            if($(item).get(0).innerText ==xwcontent.cxxh ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(yj,function(index,item){
            if($(item).get(0).innerText ==xwcontent.yj ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(jz,function(index,item){
            if($(item).get(0).innerText ==xwcontent.jz ){
              $(item).attr("class","yf_content_select");
            }  
          });
        $.each(power,function(index,item){
            if($(item).get(0).innerText ==xwcontent.power ){
              $(item).attr("class","yf_content_select");
            }  
          });
     }
      //用法选择样式渲染 
     $(".usage div").click(function(){
       //var text =$(this).get(0).innerText;
       var siblings = $(this).siblings();
       $.each(siblings,function(index,item){
          $(item).attr("class","yf_content");
       });
       //var boolean =false;
       $(this).attr("class","yf_content_select");
       /*$.each(items,function(index,item){
       	if(item.ctype ==$(".menu_select").get(0).innerText){
       		boolean = true;
       	}
       });*/
       /*if(boolean){
       //分情况
       if($(".menu_select").get(0).innerText=="艾灸"){
       	updatexwContents_aj(name,$("#time .yf_content_select").get(0).innerText,
       			              $("#type .yf_content_select").get(0).innerText,
       			              $("#bx .yf_content_select").get(0).innerText,
       			              $("#jl .yf_content_select").get(0).innerText);
       }
       if($(".menu_select").get(0).innerText=="拔罐"){
       	updatexwContents_bg(name,$("#gj .yf_content_select").get(0).innerText,
       		                 $("#zz_method .yf_content_select").get(0).innerText,
       		                 $("#time .yf_content_select").get(0).innerText,
       		                 $("#other_gf .yf_content_select").get(0).innerText
       		                   );
       }
       if($(".menu_select").get(0).innerText=="埋线"){
       	updatexwContents_mx(name,$("#cxxh .yf_content_select").get(0).innerText);
       }
       if($(".menu_select").get(0).innerText=="小针刀"){
       	updatexwContents_xzd(name,$("#yj .yf_content_select").get(0).innerText);
       }
       if($(".menu_select").get(0).innerText=="刮痧"){
       	updatexwContents_gs(name,$("#yj .yf_content_select").get(0).innerText,
       		                 $("#jz .yf_content_select").get(0).innerText,
       		                 $("#bx .yf_content_select").get(0).innerText,
       		                 $("#time .yf_content_select").get(0).innerText
       		                   );
       }
       if($(".menu_select").get(0).innerText=="牵引"){
       	updatexwContents_qy(name,$("#power .yf_content_select").get(0).innerText,
       		                 $("#time .yf_content_select").get(0).innerText
       		                   );
       }
       if($(".menu_select").get(0).innerText=="针刺"){	
       	updatexwContents(name,
       			$("#time .yf_content_select").get(0).innerText,
       			$("#zz_method .yf_content_select").get(0).innerText,
       			$("#freq .yf_content_select").get(0).innerText,
       			$("#waveform .yf_content_select").get(0).innerText,
       			$("#bx .yf_content_select").get(0).innerText
       			//$("#strength .yf_content_select").get(0).innerText
       			//$("#jd .yf_content_select").get(0).innerText,
       			//$("#sd .yf_content_select").get(0).innerText);
       );};
       }*/
     });
     //xw对象list的更新
     function updatexwContents_qy(name,power,time){
   	  $.each(items,function(index,item){
   		  if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
   			  item.power=power;
   			  item.time=time;
   		  }
   	  });
     }
     function updatexwContents_gs(name,yj,jz,bx,time){
   	  $.each(items,function(index,item){
   		  if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
   			  item.yj=yj;
   			  item.jz=jz;
   			  item.bx=bx;
   			  item.time=time;
   		  }
   	  });
     }
     function updatexwContents_xzd(name,yj){
   	  $.each(items,function(index,item){
   		  if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
   			  item.yj=yj;
   		  }
   	  });
     }
     function updatexwContents_mx(name,cxxh){
   	  $.each(items,function(index,item){
   		  if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
   			  item.time=cxxh;
   		  }
   	  });
     }
     function updatexwContents(name,time, zz_method, freq, waveform, bx){
	       $.each(items,function(index,item){
	          if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
	        	  item.time=time;
	        	  item.zz_method=zz_method;
	        	  item.freq=freq;
	        	  item.waveform=waveform;
                  item.bx=bx;
	        	  //item.strength=strength;
	        	  /*item.jd=jd;
	        	  item.sd=sd;*/
	          }
	       });
     }
     function updatexwContents_aj(name,time,type,bx,jl){
   	  $.each(items,function(index,item){
             if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
           	  item.time=time;
           	  item.type=type;
           	  item.bx=bx;
           	  item.jl=jl;
             }
          });
     }
     function updatexwContents_bg(name,gj,zz_method,time,other_gf){
   	  $.each(items,function(index,item){
   		  if(item.name==name&&item.ctype==$(".menu_select").get(0).innerText){
   			  item.gj=gj;
   			  item.zz_method=zz_method;
   			  item.time=time;
   			  item.other_gf=other_gf;
   		  }
   	  });
     }
     // 样式重置(清空)------------张登朝
     function reset(){
         recipelItems.splice(0,recipelItems.length);
         names.splice(0,names.length);
         lastName = "";
         restoreDefault();
         /*var op =$(".usage");
         $.each(op,function(index,item){
             $.each($(item).children(),function(i,p){
                 $(p).attr("class","yf_content");
             });
             $(item).children().first().attr("class","yf_content_select");
         });*/


         var xws =$("#xws");
         $.each(xws,function(index,item){
             $.each($(item).children(),function(i,p){
                 $(p).attr("class","xw_content");
             });
             $(item).children().first().attr("class","xw_content_select");
         });

         $("#xws").html("");//添加的
         $("#name").html("");
         var _temp_SelectRow = $("#serachdiv").combogrid("grid").datagrid('getSelected');
         var _var_Combgrid_SelectValue = $("#serachdiv").combogrid('getValue');
         if (!_temp_SelectRow || _var_Combgrid_SelectValue != _temp_SelectRow.guid) {//没有选择或者选项不相等时清除内容
             $("#serachdiv").combogrid('setValue', '');
         }
     }

/**
 * 用法恢复默认状态
 */
function restoreDefault() {
    var op =$(".usage");
    var yf = new Array();
    $.each(op,function(index,item){
        //alert($(item).children().length);
        $.each($(item).children(),function(i,p){
            $(p).attr("class","yf_content");
            if ($(item).children().length == 4) {
                yf.push($(p));
            }
        });
        $(item).children().first().attr("class","yf_content_select");
    });
    yf[0].attr("class","yf_content");
    yf[1].attr("class","yf_content_select");
}

     //测试函数
     function display() {
         for (var k = 0;k < recipelItems.length;k++) {
             alert(recipelItems[k].name+","+recipelItems[k].time+","+recipelItems[k].zzMethod+","+names[k]);
         }
     }