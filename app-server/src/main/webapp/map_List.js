(function(){

    "use strict"

    const qs = x => document.querySelector(x);
    const placeData = [
    {
        "address_name": "제주특별자치도 제주시 오등동 산 182",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 섬",
        "distance": "",
        "id": "18514310",
        "phone": "",
        "place_name": "제주도",
        "place_url": "http://place.map.kakao.com/18514310",
        "road_address_name": "",
        "x": "126.54587355630036",
        "y": "33.379777816446165"
    },
    {
        "address_name": "제주특별자치도 서귀포시 서귀동 316-1",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "11635431",
        "phone": "",
        "place_name": "올레길 7코스(서귀포-월평 올레)",
        "place_url": "http://place.map.kakao.com/11635431",
        "road_address_name": "제주특별자치도 서귀포시 중정로 22",
        "x": "126.51530966206293",
        "y": "33.23738579332568"
    },
    {
        "address_name": "제주특별자치도 서귀포시 보목동",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "8015963",
        "phone": "",
        "place_name": "올레길 6코스(쇠소깍-서귀포 올레)",
        "place_url": "http://place.map.kakao.com/8015963",
        "road_address_name": "",
        "x": "126.59230607000688",
        "y": "33.24127212174994"
    },
    {
        "address_name": "제주특별자치도 서귀포시 성산읍 시흥리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "10626234",
        "phone": "",
        "place_name": "올레길 1코스(시흥-광치기 올레)",
        "place_url": "http://place.map.kakao.com/10626234",
        "road_address_name": "",
        "x": "126.90636648646604",
        "y": "33.47866074455451"
    },
    {
        "address_name": "제주특별자치도 제주시 삼양이동",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "13597626",
        "phone": "",
        "place_name": "올레길 18코스(제주원도심-조천 올레)",
        "place_url": "http://place.map.kakao.com/13597626",
        "road_address_name": "",
        "x": "126.58214303914238",
        "y": "33.52522810308715"
    },
    {
        "address_name": "제주특별자치도 서귀포시 남원읍 남원리 91-6",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "11828668",
        "phone": "",
        "place_name": "올레길 5코스(남원-쇠소깍 올레)",
        "place_url": "http://place.map.kakao.com/11828668",
        "road_address_name": "",
        "x": "126.66743988738492",
        "y": "33.27078076230098"
    },
    {
        "address_name": "제주특별자치도 제주시 도두일동",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753473",
        "phone": "",
        "place_name": "올레길 17코스(광령-제주원도심 올레)",
        "place_url": "http://place.map.kakao.com/12753473",
        "road_address_name": "",
        "x": "126.4726047828175",
        "y": "33.50563877194622"
    },
    {
        "address_name": "제주특별자치도 서귀포시 대정읍 상모리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753435",
        "phone": "",
        "place_name": "올레길 10코스(화순-모슬포 올레)",
        "place_url": "http://place.map.kakao.com/12753435",
        "road_address_name": "",
        "x": "126.29320987840607",
        "y": "33.216890444802004"
    },
    {
        "address_name": "제주특별자치도 제주시 한경면 조수리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753454",
        "phone": "",
        "place_name": "올레길 13코스(용수-저지 올레)",
        "place_url": "http://place.map.kakao.com/12753454",
        "road_address_name": "",
        "x": "126.21230336658091",
        "y": "33.328625315540165"
    },
    {
        "address_name": "제주특별자치도 서귀포시 하예동 532-1",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "11792904",
        "phone": "",
        "place_name": "올레길 8코스(월평-대평 올레)",
        "place_url": "http://place.map.kakao.com/11792904",
        "road_address_name": "",
        "x": "126.41392216745272",
        "y": "33.244620421815895"
    },
    {
        "address_name": "제주특별자치도 제주시 구좌읍 행원리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "17605888",
        "phone": "",
        "place_name": "올레길 20코스(김녕-하도 올레)",
        "place_url": "http://place.map.kakao.com/17605888",
        "road_address_name": "",
        "x": "126.80547165134814",
        "y": "33.55424539993182"
    },
    {
        "address_name": "제주특별자치도 제주시 애월읍 고내리 1111-2",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "11943943",
        "phone": "",
        "place_name": "올레길 16코스(고내-광령 올레)",
        "place_url": "http://place.map.kakao.com/11943943",
        "road_address_name": "",
        "x": "126.38979561557086",
        "y": "33.46649759191228"
    },
    {
        "address_name": "제주특별자치도 제주시 조천읍 조천리 1142",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "14617417",
        "phone": "",
        "place_name": "올레길 19코스(조천-김녕 올레)",
        "place_url": "http://place.map.kakao.com/14617417",
        "road_address_name": "",
        "x": "126.6890041888081",
        "y": "33.54625032708214"
    },
    {
        "address_name": "제주특별자치도 서귀포시 표선면 표선리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753459",
        "phone": "",
        "place_name": "올레길 4코스(표선-남원 올레)",
        "place_url": "http://place.map.kakao.com/12753459",
        "road_address_name": "",
        "x": "126.78792799112594",
        "y": "33.30481622383354"
    },
    {
        "address_name": "제주특별자치도 서귀포시 성산읍 고성리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753467",
        "phone": "",
        "place_name": "올레길 2코스(광치기-온평 올레)",
        "place_url": "http://place.map.kakao.com/12753467",
        "road_address_name": "",
        "x": "126.90186955925942",
        "y": "33.44016934645293"
    },
    {
        "address_name": "제주특별자치도 서귀포시 안덕면 감산리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753461",
        "phone": "",
        "place_name": "올레길 9코스(대평-화순 올레)",
        "place_url": "http://place.map.kakao.com/12753461",
        "road_address_name": "",
        "x": "126.3483591501524",
        "y": "33.242674889021984"
    },
    {
        "address_name": "제주특별자치도 제주시 한림읍 월령리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753448",
        "phone": "",
        "place_name": "올레길 14코스(저지-한림 올레)",
        "place_url": "http://place.map.kakao.com/12753448",
        "road_address_name": "",
        "x": "126.22960827746336",
        "y": "33.36565953780862"
    },
    {
        "address_name": "제주특별자치도 서귀포시 대정읍 신도리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753460",
        "phone": "",
        "place_name": "올레길 12코스(무릉-용수 올레)",
        "place_url": "http://place.map.kakao.com/12753460",
        "road_address_name": "",
        "x": "126.19069592022394",
        "y": "33.279158241153986"
    },
    {
        "address_name": "제주특별자치도 제주시 한경면 저지리 1792-3",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12016339",
        "phone": "",
        "place_name": "올레길 14-1코스(저지-서광 올레)",
        "place_url": "http://place.map.kakao.com/12016339",
        "road_address_name": "",
        "x": "126.28596960580299",
        "y": "33.322110252982824"
    },
    {
        "address_name": "제주특별자치도 제주시 구좌읍 하도리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "18502107",
        "phone": "",
        "place_name": "올레길 21코스",
        "place_url": "http://place.map.kakao.com/18502107",
        "road_address_name": "",
        "x": "126.89827021157316",
        "y": "33.51281074786431"
    },
    {
        "address_name": "제주특별자치도 제주시 애월읍 금성리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "11816186",
        "phone": "",
        "place_name": "올레길 15-A코스(한림-고내 올레)",
        "place_url": "http://place.map.kakao.com/11816186",
        "road_address_name": "",
        "x": "126.31156024250767",
        "y": "33.432757960725944"
    },
    {
        "address_name": "제주특별자치도 서귀포시 대정읍 동일리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753598",
        "phone": "",
        "place_name": "올레길 11코스(모슬포-무릉 올레)",
        "place_url": "http://place.map.kakao.com/12753598",
        "road_address_name": "",
        "x": "126.2582191245854",
        "y": "33.253075844297754"
    },
    {
        "address_name": "제주특별자치도 제주시 한림읍 귀덕리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "1260224365",
        "phone": "",
        "place_name": "올레길 15-B코스(한림-고내 올레)",
        "place_url": "http://place.map.kakao.com/1260224365",
        "road_address_name": "",
        "x": "126.2967483721911",
        "y": "33.44696680356802"
    },
    {
        "address_name": "제주특별자치도 제주시 우도면 연평리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753592",
        "phone": "",
        "place_name": "올레길 1-1코스(우도-올레)",
        "place_url": "http://place.map.kakao.com/12753592",
        "road_address_name": "",
        "x": "126.95576717318423",
        "y": "33.49633806057723"
    },
    {
        "address_name": "제주특별자치도 서귀포시 서호동",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753593",
        "phone": "",
        "place_name": "올레길 7-1코스(월드컵경기장-서귀포 올레)",
        "place_url": "http://place.map.kakao.com/12753593",
        "road_address_name": "",
        "x": "126.5232266849254",
        "y": "33.26209936589624"
    },
    {
        "address_name": "제주특별자치도 서귀포시 성산읍 온평리 1001-4",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753453",
        "phone": "",
        "place_name": "올레길 3-A코스(온평-표선 올레)",
        "place_url": "http://place.map.kakao.com/12753453",
        "road_address_name": "",
        "x": "126.86075182230402",
        "y": "33.37140152796935"
    },
    {
        "address_name": "제주특별자치도 서귀포시 성산읍 삼달리",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "903326529",
        "phone": "",
        "place_name": "올레길 3-B코스(온평-표선 올레)",
        "place_url": "http://place.map.kakao.com/903326529",
        "road_address_name": "제주특별자치도 서귀포시 성산읍 환해장성로 136",
        "x": "126.87096741298276",
        "y": "33.36659068869355"
    },
    {
        "address_name": "제주특별자치도 서귀포시 대정읍 가파리 280-1",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 도보여행 > 올레길 > 제주올레길",
        "distance": "",
        "id": "12753433",
        "phone": "",
        "place_name": "올레길 10-1코스(가파도 올레)",
        "place_url": "http://place.map.kakao.com/12753433",
        "road_address_name": "제주특별자치도 서귀포시 대정읍 가파로91번길 51",
        "x": "126.27108690024005",
        "y": "33.17036186293198"
    },
    {
        "address_name": "제주특별자치도 제주시 구좌읍 송당리 2303-2",
        "category_group_code": "",
        "category_group_name": "",
        "category_name": "여행 > 관광,명소",
        "distance": "",
        "id": "251549382",
        "phone": "064-784-0930",
        "place_name": "스누피가든",
        "place_url": "http://place.map.kakao.com/251549382",
        "road_address_name": "제주특별자치도 제주시 구좌읍 금백조로 930",
        "x": "126.77844424339503",
        "y": "33.443962924091636"
    },
    {
        "address_name": "제주특별자치도 제주시 조천읍 함덕리 1004-10",
        "category_group_code": "AT4",
        "category_group_name": "관광명소",
        "category_name": "여행 > 관광,명소 > 해수욕장,해변",
        "distance": "",
        "id": "8148451",
        "phone": "064-728-3989",
        "place_name": "함덕해수욕장",
        "place_url": "http://place.map.kakao.com/8148451",
        "road_address_name": "제주특별자치도 제주시 조천읍 조함해안로 525",
        "x": "126.669238934013",
        "y": "33.5430615661113"
    }

    
    ]
    const $placesList = qs("#placesList");

    // 마커를 담을 배열입니다
    let markers = [];

    let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.3700, 126.4500), // 지도의 중심좌표
            level: 8 // 지도의 확대 레벨
        };  

    // 지도를 생성합니다    
    let map = new kakao.maps.Map(mapContainer, mapOption); 

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({zIndex:1});


    function mapApi() {
        const regex = /[^0-9]/g;

        function init() {
            displayPlaces(placeData);
            placeListClickEvent();
            moveMapLocationEvent();
        }

        // 검색 결과 목록과 마커를 표출하는 함수입니다
        function displayPlaces(places) {

            let listEl = document.getElementById('placesList');
            let menuEl = document.getElementById('menu_wrap');
            let fragment = document.createDocumentFragment();
            let bounds = new kakao.maps.LatLngBounds();
            
            for ( let i=0; i<places.length; i++ ) {

                // 마커를 생성하고 지도에 표시합니다
                let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
                let marker = addMarker(placePosition, i);
                let itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);

                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                (function(marker, title) {
                    kakao.maps.event.addListener(marker, 'mouseover', function () {
                        displayInfowindow(marker, title);
                    });

                    kakao.maps.event.addListener(marker, 'mouseout', function() {
                        infowindow.close();
                    });

                    itemEl.onmouseover =  function () {
                        displayInfowindow(marker, title);
                    };

                    itemEl.onmouseout =  function () {
                        infowindow.close();
                    };
                })(marker, places[i].place_name);

                fragment.appendChild(itemEl);
            }

            // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
            listEl.appendChild(fragment);
            menuEl.scrollTop = 0;

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            // map.setBounds(bounds);
        }

        // 검색결과 항목을 Element로 반환하는 함수입니다
        function getListItem(index, places) {

            let el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                        '<div class="info">' +
                        '   <h5>' + places.place_name + '</h5>';

            if (places.road_address_name) {
                itemStr += '    <span>' + places.road_address_name + '</span>' +
                            '   <span class="jibun gray">' +  places.address_name  + '</span>';
            } else {
                itemStr += '    <span>' +  places.address_name  + '</span>'; 
            }
                        
            itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                        '</div>';
            
            
            el.innerHTML = itemStr;
            el.className = 'item';

            return el;
        }

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker(position, idx, title) {
            let imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'; // 마커 이미지 url, 스프라이트 이미지를 씁니다
            let imageSize = new kakao.maps.Size(24, 35);  // 마커 이미지의 크기

            let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
            let marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage 
            });

            marker.setMap(map); // 지도 위에 마커를 표출합니다
            markers.push(marker);  // 배열에 생성된 마커를 추가합니다

            return marker;
        }

        // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
        // 인포윈도우에 장소명을 표시합니다
        function displayInfowindow(marker, title) {
            let content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

            infowindow.setContent(content);
            infowindow.open(map, marker);
        }

        function placeListClickEvent() {
            $placesList.addEventListener('click', (e) => {
                let liTag = e.target.closest('li'); 
                if (!liTag) return; 
                if (!$placesList.contains(liTag)) return;

                let selectedPlaceItemNum = parseInt(liTag.childNodes[0].className.replace(regex, ""));
                
                console.log(placeData[selectedPlaceItemNum-1].x);
            });
        }

        function moveMapLocationEvent() {
            $placesList.addEventListener('mouseover', (e) => {
                let liTag = e.target.closest('li'); 
                if (!liTag) return; 
                if (!$placesList.contains(liTag)) return;

                let selectedPlaceItemNum = parseInt(liTag.childNodes[0].className.replace(regex, ""));
                
                // 이동할 위도 경도 위치를 생성합니다 
                let moveLatLon = new kakao.maps.LatLng(placeData[selectedPlaceItemNum-1].y, placeData[selectedPlaceItemNum-1].x);
                
                // 지도 중심을 부드럽게 이동시킵니다
                // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
                map.panTo(moveLatLon);
            });
        }

        init();
    }
    mapApi();
    
})();