package com.manage.project.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.project.mapper.SSQMapper;
import com.manage.project.model.SSQDetail;
import com.manage.project.service.LotteryService;
import com.manage.project.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    SSQMapper ssqMapper;
    @Override
    public int digData() {
        //String s = HttpUtil.doGet("http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&pageNo=1&pageSize=30&systemType=PC");
        //String st = "{\"state\":0,\"message\":\"查询成功\",\"total\":1603,\"pageNum\":54,\"pageNo\":1,\"pageSize\":30,\"Tflag\":0,\"result\":[{\"name\":\"双色球\",\"code\":\"2023098\",\"detailsLink\":\"/c/2023/08/24/551414.shtml\",\"videoLink\":\"/c/2023/08/24/551413.shtml\",\"date\":\"2023-08-24(四)\",\"week\":\"四\",\"red\":\"02,10,11,14,21,27\",\"blue\":\"11\",\"blue2\":\"\",\"sales\":\"362199042\",\"poolmoney\":\"2118259570\",\"content\":\"辽宁1注,浙江2注,江西2注,山东1注,重庆1注,河南1注,四川2注,贵州1注,陕西1注,深圳1注,共13注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"13\",\"typemoney\":\"6522117\"},{\"type\":2,\"typenum\":\"266\",\"typemoney\":\"92986\"},{\"type\":3,\"typenum\":\"1634\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"79485\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1454255\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"8639674\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023097\",\"detailsLink\":\"/c/2023/08/22/551196.shtml\",\"videoLink\":\"/c/2023/08/22/551194.shtml\",\"date\":\"2023-08-22(二)\",\"week\":\"二\",\"red\":\"01,09,14,19,29,30\",\"blue\":\"15\",\"blue2\":\"\",\"sales\":\"357725994\",\"poolmoney\":\"2128843885\",\"content\":\"北京1注,河北1注,吉林1注,江西1注,海南1注,四川3注,云南5注,共13注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"13\",\"typemoney\":\"6311615\"},{\"type\":2,\"typenum\":\"225\",\"typemoney\":\"94727\"},{\"type\":3,\"typenum\":\"2256\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"108823\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1767679\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"8764273\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023096\",\"detailsLink\":\"/c/2023/08/20/550976.shtml\",\"videoLink\":\"/c/2023/08/20/550974.shtml\",\"date\":\"2023-08-20(日)\",\"week\":\"日\",\"red\":\"08,11,18,26,31,32\",\"blue\":\"15\",\"blue2\":\"\",\"sales\":\"387715200\",\"poolmoney\":\"2146953645\",\"content\":\"河北2注,山西1注,辽宁2注,安徽1注,四川1注,云南1注,共8注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"8\",\"typemoney\":\"7789981\"},{\"type\":2,\"typenum\":\"125\",\"typemoney\":\"223198\"},{\"type\":3,\"typenum\":\"1156\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"67955\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1302927\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"9658580\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023095\",\"detailsLink\":\"/c/2023/08/17/550848.shtml\",\"videoLink\":\"/c/2023/08/17/550846.shtml\",\"date\":\"2023-08-17(四)\",\"week\":\"四\",\"red\":\"03,08,16,17,22,26\",\"blue\":\"11\",\"blue2\":\"\",\"sales\":\"357870744\",\"poolmoney\":\"2125574043\",\"content\":\"河北7注,山西1注,浙江1注,福建1注,江西2注,山东3注,重庆1注,河南1注,广东1注,广西1注,四川1注,陕西1注,宁夏1注,共22注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"22\",\"typemoney\":\"5751004\"},{\"type\":2,\"typenum\":\"342\",\"typemoney\":\"60387\"},{\"type\":3,\"typenum\":\"2162\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"109563\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1818621\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"9232272\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023094\",\"detailsLink\":\"/c/2023/08/15/550628.shtml\",\"videoLink\":\"/c/2023/08/15/550626.shtml\",\"date\":\"2023-08-15(二)\",\"week\":\"二\",\"red\":\"06,10,12,14,30,31\",\"blue\":\"12\",\"blue2\":\"\",\"sales\":\"353863692\",\"poolmoney\":\"2190138271\",\"content\":\"浙江1注,福建2注,山东8注,四川1注,陕西1注,共13注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"13\",\"typemoney\":\"6482678\"},{\"type\":2,\"typenum\":\"62\",\"typemoney\":\"388605\"},{\"type\":3,\"typenum\":\"1054\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"57805\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1154774\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"10149675\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023093\",\"detailsLink\":\"/c/2023/08/13/550308.shtml\",\"videoLink\":\"/c/2023/08/13/550307.shtml\",\"date\":\"2023-08-13(日)\",\"week\":\"日\",\"red\":\"10,21,24,25,27,32\",\"blue\":\"07\",\"blue2\":\"\",\"sales\":\"382160878\",\"poolmoney\":\"2202132520\",\"content\":\"河北1注,陕西1注,共2注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"2\",\"typemoney\":\"10000000\"},{\"type\":2,\"typenum\":\"55\",\"typemoney\":\"500947\"},{\"type\":3,\"typenum\":\"838\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"47779\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1029761\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"10936581\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023092\",\"detailsLink\":\"/c/2023/08/10/550181.shtml\",\"videoLink\":\"/c/2023/08/10/550180.shtml\",\"date\":\"2023-08-10(四)\",\"week\":\"四\",\"red\":\"03,12,24,25,32,33\",\"blue\":\"13\",\"blue2\":\"\",\"sales\":\"357345052\",\"poolmoney\":\"2139476134\",\"content\":\"浙江3注,山东1注,陕西2注,共6注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"6\",\"typemoney\":\"8563809\"},{\"type\":2,\"typenum\":\"98\",\"typemoney\":\"272740\"},{\"type\":3,\"typenum\":\"1075\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"63928\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1253157\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"7928526\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023091\",\"detailsLink\":\"/c/2023/08/08/549962.shtml\",\"videoLink\":\"/c/2023/08/08/549961.shtml\",\"date\":\"2023-08-08(二)\",\"week\":\"二\",\"red\":\"16,20,22,26,30,32\",\"blue\":\"16\",\"blue2\":\"\",\"sales\":\"358375594\",\"poolmoney\":\"2110673283\",\"content\":\"天津1注,浙江4注,广东1注,新疆1注,深圳1注,共8注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"8\",\"typemoney\":\"7716754\"},{\"type\":2,\"typenum\":\"69\",\"typemoney\":\"393732\"},{\"type\":3,\"typenum\":\"1010\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"50480\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1012117\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"8737338\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023090\",\"detailsLink\":\"/c/2023/08/06/549744.shtml\",\"videoLink\":\"/c/2023/08/06/549742.shtml\",\"date\":\"2023-08-06(日)\",\"week\":\"日\",\"red\":\"01,02,09,26,30,31\",\"blue\":\"07\",\"blue2\":\"\",\"sales\":\"384947064\",\"poolmoney\":\"2090904684\",\"content\":\"北京1注,湖北1注,广东2注,共4注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"4\",\"typemoney\":\"9305795\"},{\"type\":2,\"typenum\":\"97\",\"typemoney\":\"221948\"},{\"type\":3,\"typenum\":\"1268\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"75044\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1492374\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"13754324\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023089\",\"detailsLink\":\"/c/2023/08/03/549615.shtml\",\"videoLink\":\"/c/2023/08/03/549614.shtml\",\"date\":\"2023-08-03(四)\",\"week\":\"四\",\"red\":\"08,13,15,27,28,31\",\"blue\":\"11\",\"blue2\":\"\",\"sales\":\"354904960\",\"poolmoney\":\"2063540939\",\"content\":\"辽宁1注,浙江1注,安徽1注,江西1注,河南4注,广东3注,贵州5注,甘肃1注,新疆1注,共18注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"18\",\"typemoney\":\"5770016\"},{\"type\":2,\"typenum\":\"122\",\"typemoney\":\"142011\"},{\"type\":3,\"typenum\":\"1722\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"84839\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1438561\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"13616505\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023088\",\"detailsLink\":\"/c/2023/08/01/549396.shtml\",\"videoLink\":\"/c/2023/08/01/549395.shtml\",\"date\":\"2023-08-01(二)\",\"week\":\"二\",\"red\":\"01,14,18,26,31,33\",\"blue\":\"05\",\"blue2\":\"\",\"sales\":\"350096092\",\"poolmoney\":\"2115425117\",\"content\":\"河北1注,内蒙古5注,浙江1注,山东1注,湖南2注,四川1注,共11注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"11\",\"typemoney\":\"6088495\"},{\"type\":2,\"typenum\":\"98\",\"typemoney\":\"152722\"},{\"type\":3,\"typenum\":\"1326\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"70806\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1270407\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"16167318\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023087\",\"detailsLink\":\"/c/2023/07/30/549077.shtml\",\"videoLink\":\"/c/2023/07/30/549075.shtml\",\"date\":\"2023-07-30(日)\",\"week\":\"日\",\"red\":\"02,09,15,17,28,33\",\"blue\":\"01\",\"blue2\":\"\",\"sales\":\"381153750\",\"poolmoney\":\"2137498144\",\"content\":\"辽宁1注,江苏1注,山东1注,河南1注,湖南1注,四川2注,云南1注,陕西1注,共9注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"9\",\"typemoney\":\"7457024\"},{\"type\":2,\"typenum\":\"158\",\"typemoney\":\"174946\"},{\"type\":3,\"typenum\":\"1754\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"74057\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1480314\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"8264542\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023086\",\"detailsLink\":\"/c/2023/07/27/548946.shtml\",\"videoLink\":\"/c/2023/07/27/548945.shtml\",\"date\":\"2023-07-27(四)\",\"week\":\"四\",\"red\":\"08,09,10,22,26,32\",\"blue\":\"12\",\"blue2\":\"\",\"sales\":\"357436118\",\"poolmoney\":\"2121686796\",\"content\":\"浙江1注,福建1注,山东2注,广东2注,广西2注,四川1注,贵州1注,宁夏1注,共11注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"11\",\"typemoney\":\"6685067\"},{\"type\":2,\"typenum\":\"185\",\"typemoney\":\"125241\"},{\"type\":3,\"typenum\":\"3031\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"83557\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1571421\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"8189276\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023085\",\"detailsLink\":\"/c/2023/07/25/548628.shtml\",\"videoLink\":\"/c/2023/07/25/548626.shtml\",\"date\":\"2023-07-25(二)\",\"week\":\"二\",\"red\":\"11,18,23,24,31,33\",\"blue\":\"13\",\"blue2\":\"\",\"sales\":\"356240472\",\"poolmoney\":\"2125713507\",\"content\":\"辽宁1注,浙江1注,湖北1注,广西1注,四川1注,云南1注,共6注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"6\",\"typemoney\":\"8156360\"},{\"type\":2,\"typenum\":\"97\",\"typemoney\":\"244048\"},{\"type\":3,\"typenum\":\"1403\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"71640\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1352674\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"9560653\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023084\",\"detailsLink\":\"/c/2023/07/23/548308.shtml\",\"videoLink\":\"/c/2023/07/23/548306.shtml\",\"date\":\"2023-07-23(日)\",\"week\":\"日\",\"red\":\"09,13,14,17,19,27\",\"blue\":\"03\",\"blue2\":\"\",\"sales\":\"386580634\",\"poolmoney\":\"2103633553\",\"content\":\"黑龙江1注,浙江1注,安徽3注,共5注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"5\",\"typemoney\":\"9140598\"},{\"type\":2,\"typenum\":\"99\",\"typemoney\":\"261401\"},{\"type\":3,\"typenum\":\"1274\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"73182\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1429863\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"10630502\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023083\",\"detailsLink\":\"/c/2023/07/20/548080.shtml\",\"videoLink\":\"/c/2023/07/20/548081.shtml\",\"date\":\"2023-07-20(四)\",\"week\":\"四\",\"red\":\"07,10,12,16,17,30\",\"blue\":\"12\",\"blue2\":\"\",\"sales\":\"360195880\",\"poolmoney\":\"2071700320\",\"content\":\"黑龙江1注,江苏1注,湖北1注,广西1注,陕西1注,共5注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"5\",\"typemoney\":\"7947836\"},{\"type\":2,\"typenum\":\"141\",\"typemoney\":\"130666\"},{\"type\":3,\"typenum\":\"1871\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"91206\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1641413\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"12506349\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023082\",\"detailsLink\":\"/c/2023/07/18/547765.shtml\",\"videoLink\":\"/c/2023/07/18/547761.shtml\",\"date\":\"2023-07-18(二)\",\"week\":\"二\",\"red\":\"04,18,19,24,27,30\",\"blue\":\"16\",\"blue2\":\"\",\"sales\":\"355121956\",\"poolmoney\":\"2056167572\",\"content\":\"黑龙江1注,江苏1注,浙江1注,江西2注,山东2注,河南1注,广东1注,贵州1注,云南1注,陕西1注,甘肃1注,共13注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"13\",\"typemoney\":\"6130757\"},{\"type\":2,\"typenum\":\"144\",\"typemoney\":\"127602\"},{\"type\":3,\"typenum\":\"1654\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"83863\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1490226\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"12774735\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023081\",\"detailsLink\":\"/c/2023/07/16/547443.shtml\",\"videoLink\":\"/c/2023/07/16/547441.shtml\",\"date\":\"2023-07-16(日)\",\"week\":\"日\",\"red\":\"01,11,15,16,22,31\",\"blue\":\"15\",\"blue2\":\"\",\"sales\":\"387109286\",\"poolmoney\":\"2080743000\",\"content\":\"河北1注,江苏3注,广东1注,深圳1注,共6注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"6\",\"typemoney\":\"8525084\"},{\"type\":2,\"typenum\":\"53\",\"typemoney\":\"498832\"},{\"type\":3,\"typenum\":\"974\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"59067\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1137089\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"11564948\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023080\",\"detailsLink\":\"/c/2023/07/13/547214.shtml\",\"videoLink\":\"/c/2023/07/13/547213.shtml\",\"date\":\"2023-07-13(四)\",\"week\":\"四\",\"red\":\"01,02,04,09,25,31\",\"blue\":\"08\",\"blue2\":\"\",\"sales\":\"363086950\",\"poolmoney\":\"2052579114\",\"content\":\"内蒙古1注,辽宁1注,江苏1注,浙江1注,福建1注,四川1注,云南3注,共9注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"9\",\"typemoney\":\"7025698\"},{\"type\":2,\"typenum\":\"66\",\"typemoney\":\"345289\"},{\"type\":3,\"typenum\":\"1157\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"62549\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1223795\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"11707483\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023079\",\"detailsLink\":\"/c/2023/07/11/546996.shtml\",\"videoLink\":\"/c/2023/07/11/546994.shtml\",\"date\":\"2023-07-11(二)\",\"week\":\"二\",\"red\":\"05,06,09,12,21,22\",\"blue\":\"07\",\"blue2\":\"\",\"sales\":\"352893610\",\"poolmoney\":\"2047443072\",\"content\":\"北京1注,河北1注,内蒙古1注,辽宁1注,吉林1注,浙江16注,山东3注,湖北1注,湖南2注,广东15注,云南1注,陕西1注,共44注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"44\",\"typemoney\":\"5140313\"},{\"type\":2,\"typenum\":\"219\",\"typemoney\":\"35238\"},{\"type\":3,\"typenum\":\"3330\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"135991\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"2080932\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"16810255\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023078\",\"detailsLink\":\"/c/2023/07/09/546676.shtml\",\"videoLink\":\"/c/2023/07/09/546674.shtml\",\"date\":\"2023-07-09(日)\",\"week\":\"日\",\"red\":\"06,08,14,19,30,31\",\"blue\":\"08\",\"blue2\":\"\",\"sales\":\"376825490\",\"poolmoney\":\"2250465082\",\"content\":\"天津2注,辽宁1注,吉林1注,黑龙江1注,江苏5注,福建2注,河南1注,湖南1注,广东3注,四川2注,贵州1注,云南2注,共22注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"22\",\"typemoney\":\"5429023\"},{\"type\":2,\"typenum\":\"134\",\"typemoney\":\"88045\"},{\"type\":3,\"typenum\":\"3761\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"108511\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1792578\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"17308186\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023077\",\"detailsLink\":\"/c/2023/07/06/546448.shtml\",\"videoLink\":\"/c/2023/07/06/546447.shtml\",\"date\":\"2023-07-06(四)\",\"week\":\"四\",\"red\":\"07,09,14,22,26,27\",\"blue\":\"01\",\"blue2\":\"\",\"sales\":\"351174612\",\"poolmoney\":\"2334509163\",\"content\":\"辽宁1注,黑龙江1注,上海1注,浙江1注,广东2注,云南1注,陕西1注,共8注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"8\",\"typemoney\":\"7356811\"},{\"type\":2,\"typenum\":\"232\",\"typemoney\":\"101586\"},{\"type\":3,\"typenum\":\"1612\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"90881\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1742154\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"7473868\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023076\",\"detailsLink\":\"/c/2023/07/04/546229.shtml\",\"videoLink\":\"/c/2023/07/04/546227.shtml\",\"date\":\"2023-07-04(二)\",\"week\":\"二\",\"red\":\"03,04,15,18,19,22\",\"blue\":\"09\",\"blue2\":\"\",\"sales\":\"349981630\",\"poolmoney\":\"2322659300\",\"content\":\"内蒙古1注,辽宁1注,安徽1注,山东4注,广东1注,共8注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"8\",\"typemoney\":\"7228807\"},{\"type\":2,\"typenum\":\"92\",\"typemoney\":\"242261\"},{\"type\":3,\"typenum\":\"1291\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"65051\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1177669\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"10735760\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023075\",\"detailsLink\":\"/c/2023/07/02/545910.shtml\",\"videoLink\":\"/c/2023/07/02/545908.shtml\",\"date\":\"2023-07-02(日)\",\"week\":\"日\",\"red\":\"13,15,24,28,30,31\",\"blue\":\"01\",\"blue2\":\"\",\"sales\":\"382962350\",\"poolmoney\":\"2313625531\",\"content\":\"河北1注,辽宁1注,福建2注,湖南3注,四川1注,新疆1注,共9注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"9\",\"typemoney\":\"6942068\"},{\"type\":2,\"typenum\":\"107\",\"typemoney\":\"204189\"},{\"type\":3,\"typenum\":\"1526\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"81441\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1516836\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"12844785\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023074\",\"detailsLink\":\"/c/2023/06/29/545583.shtml\",\"videoLink\":\"/c/2023/06/29/545581.shtml\",\"date\":\"2023-06-29(四)\",\"week\":\"四\",\"red\":\"02,08,09,18,19,21\",\"blue\":\"03\",\"blue2\":\"\",\"sales\":\"358471886\",\"poolmoney\":\"2310559345\",\"content\":\"辽宁1注,江苏1注,陕西2注,共4注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"4\",\"typemoney\":\"9084632\"},{\"type\":2,\"typenum\":\"228\",\"typemoney\":\"89575\"},{\"type\":3,\"typenum\":\"2394\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"104238\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1597358\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"9991078\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023073\",\"detailsLink\":\"/c/2023/06/27/545262.shtml\",\"videoLink\":\"/c/2023/06/27/545260.shtml\",\"date\":\"2023-06-27(二)\",\"week\":\"二\",\"red\":\"01,03,15,16,25,32\",\"blue\":\"10\",\"blue2\":\"\",\"sales\":\"360075550\",\"poolmoney\":\"2285628386\",\"content\":\"北京1注,贵州1注,云南1注,西藏1注,青海1注,共5注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"5\",\"typemoney\":\"8830568\"},{\"type\":2,\"typenum\":\"64\",\"typemoney\":\"374078\"},{\"type\":3,\"typenum\":\"1277\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"60309\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1145825\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"10664350\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023072\",\"detailsLink\":\"/c/2023/06/25/544942.shtml\",\"videoLink\":\"/c/2023/06/25/544940.shtml\",\"date\":\"2023-06-25(日)\",\"week\":\"日\",\"red\":\"02,06,10,24,25,33\",\"blue\":\"09\",\"blue2\":\"\",\"sales\":\"390203400\",\"poolmoney\":\"2257958066\",\"content\":\"吉林1注,上海1注,重庆1注,湖南4注,共7注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"7\",\"typemoney\":\"6644229\"},{\"type\":2,\"typenum\":\"197\",\"typemoney\":\"73030\"},{\"type\":3,\"typenum\":\"1940\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"89535\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1569193\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"18846544\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023071\",\"detailsLink\":\"/c/2023/06/22/544716.shtml\",\"videoLink\":\"/c/2023/06/22/544714.shtml\",\"date\":\"2023-06-22(四)\",\"week\":\"四\",\"red\":\"05,12,15,19,24,26\",\"blue\":\"06\",\"blue2\":\"\",\"sales\":\"335357710\",\"poolmoney\":\"2261306658\",\"content\":\"河北1注,辽宁2注,黑龙江1注,浙江2注,福建1注,山东1注,河南1注,湖南1注,广西2注,四川2注,甘肃2注,共16注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"16\",\"typemoney\":\"5979075\"},{\"type\":2,\"typenum\":\"127\",\"typemoney\":\"154185\"},{\"type\":3,\"typenum\":\"1726\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"83703\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1492709\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"9830707\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023070\",\"detailsLink\":\"/c/2023/06/20/544595.shtml\",\"videoLink\":\"/c/2023/06/20/544594.shtml\",\"date\":\"2023-06-20(二)\",\"week\":\"二\",\"red\":\"03,05,25,31,32,33\",\"blue\":\"04\",\"blue2\":\"\",\"sales\":\"358436376\",\"poolmoney\":\"2298227329\",\"content\":\"共0注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"0\",\"typemoney\":\"0\"},{\"type\":2,\"typenum\":\"110\",\"typemoney\":\"248023\"},{\"type\":3,\"typenum\":\"949\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"53473\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1054627\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"8483102\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]},{\"name\":\"双色球\",\"code\":\"2023069\",\"detailsLink\":\"/c/2023/06/18/544274.shtml\",\"videoLink\":\"/c/2023/06/18/544272.shtml\",\"date\":\"2023-06-18(日)\",\"week\":\"日\",\"red\":\"12,14,17,20,21,26\",\"blue\":\"02\",\"blue2\":\"\",\"sales\":\"396617204\",\"poolmoney\":\"2216379497\",\"content\":\"河北2注,浙江2注,山东1注,甘肃1注,共6注。\",\"addmoney\":\"\",\"addmoney2\":\"\",\"msg\":\"\",\"z2add\":\"\",\"m2add\":\"\",\"prizegrades\":[{\"type\":1,\"typenum\":\"6\",\"typemoney\":\"9116836\"},{\"type\":2,\"typenum\":\"129\",\"typemoney\":\"239350\"},{\"type\":3,\"typenum\":\"1279\",\"typemoney\":\"3000\"},{\"type\":4,\"typenum\":\"69052\",\"typemoney\":\"200\"},{\"type\":5,\"typenum\":\"1421434\",\"typemoney\":\"10\"},{\"type\":6,\"typenum\":\"7795120\",\"typemoney\":\"5\"},{\"type\":7,\"typenum\":\"\",\"typemoney\":\"\"}]}]}";
        File file = new File("/Users/zhujiangtao/IdeaProjects/devops-qa/src/main/resources/datafile/ssqdata2_700");
        StringBuffer sb = FileUtil.readerMethod(file);
        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
        JSONArray result = jsonObject.getJSONArray("result");
        List<SSQDetail> list = new ArrayList<>();
        for(int i = 0;i<result.size();i++){
            JSONObject jo = result.getJSONObject(i);
            String code = jo.getString("code");
            String date = jo.getString("date");
            String week = jo.getString("week");
            String red = jo.getString("red");
            String blue = jo.getString("blue");
            String sales = jo.getString("sales");
            String poolmoney = jo.getString("poolmoney");
            String content = jo.getString("content");

            SSQDetail detail = new SSQDetail();
            detail.setCode(code);
            detail.setDate(LocalDate.parse(date.substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            detail.setWeek(week);
            detail.setBlue(blue);
            detail.setRed(red);
            detail.setContent(content);
            detail.setSales(Long.parseLong(sales));
            detail.setPoolmoney(Long.parseLong(poolmoney));


            JSONArray prizegrades = jo.getJSONArray("prizegrades");

            for(int j = 0;j<prizegrades.size();j++){
                JSONObject jb = prizegrades.getJSONObject(j);
                Integer type = jb.getInteger("type");
                String typenum = jb.getString("typenum");
                String typemoney = jb.getString("typemoney");
                switch (type){
                    case 1:
                        detail.setFirstCount(Integer.parseInt(typenum));
                        detail.setFirstMoney(Integer.parseInt(typemoney.contains("（")?typemoney.substring(0,typemoney.indexOf("（")):typemoney));
                        break;
                    case 2:
                        detail.setSecondCount(Integer.parseInt(typenum));
                        detail.setSecondMoney(Integer.parseInt(typemoney.contains("（")?typemoney.substring(0,typemoney.indexOf("（")):typemoney));
                        break;
                    case 3:
                        detail.setThirdCount(Integer.parseInt(typenum));
                        detail.setThirdMoney(Integer.parseInt(typemoney.contains("（")?typemoney.substring(0,typemoney.indexOf("（")):typemoney));
                        break;
                    case 4:
                        detail.setFourthCount(Integer.parseInt(typenum));
                        detail.setFourthMoney(Integer.parseInt(typemoney.contains("（")?typemoney.substring(0,typemoney.indexOf("（")):typemoney));
                        break;
                    case 5:
                        detail.setFifthCount(Integer.parseInt(typenum));
                        detail.setFifthMoney(Integer.parseInt(typemoney.contains("（")?typemoney.substring(0,typemoney.indexOf("（")):typemoney));
                        break;
                    case 6:
                        detail.setSixthCount(Integer.parseInt(typenum));
                        detail.setSixthMoney(Integer.parseInt(typemoney.contains("（")?typemoney.substring(0,typemoney.indexOf("（")):typemoney));
                        break;
                    default:
                        break;
                }
            }
            //ssqMapper.save(detail);
            list.add(detail);
        }
        int count = ssqMapper.batchSave(list);
        return 0;
    }
}