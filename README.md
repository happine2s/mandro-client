# Mandro Eyes

Mandro Client는 **Mandro 로봇의 '눈(Eyes)' 역할**을 하는 안드로이드 클라이언트 앱입니다.  
스마트폰을 고글에 장착하면 라즈베리파이에 연결된 듀얼 카메라의 영상을 그대로 눈앞에 볼 수 있어, 마치 로봇의 시야를 직접 체험하는 듯한 경험을 제공합니다.  

이 앱은 FastAPI 기반 [Mandro Server](https://github.com/happine2s/mandro-server)와 함께 동작합니다.  

---
## 📌 배경과 문제 인식

Mandro에서 로봇을 제작하는 과정에서, 사람이 로봇의 눈으로 세상을 볼 수 있는 방법을 고민하게 됐습니다.
하지만 카메라를 실제로 장착하다 보니 다음과 같은 문제가 있었습니다:

- 카메라 모듈을 기계적으로 완벽하게 정렬·방향 맞추는 것이 번거롭고 까다로움
- 설치 환경에 따라 영상이 뒤집히거나 어긋나 몰입감이 깨짐
- 사용자가 고글을 통해 볼 때는 항상 현실과 동일한 시야가 보장되어야 함  


## 🎯 해결 방법

이를 해결하기 위해 Mandro Client는 단순 스트리밍 뷰어가 아니라   **소프트웨어적으로 카메라 상태를 보정**하는 기능을 제공합니다:

- **회전 / 반전 / 간격 조절** 기능 → 하드웨어 설치 제약 해소  
- 어떤 방향으로 카메라를 설치해도, **화면 상에서는 항상 올바른 시야 제공**  
- 앱(WebView)을 통해 서버의 스트리밍 페이지를 불러와, **모바일 환경에서 즉시 체험 가능**  


## 🚀 주요 기능

- **실시간 듀얼 카메라 스트리밍**  
  - 서버의 `/stream.html` 페이지를 WebView로 로드  
  - 고글 속 스마트폰 화면으로 로봇의 시야를 체험  

- **카메라 상태 보정**  
  - 회전(90° 단위), 좌우 반전, 화면 간격 조절  
  - 설치 방향과 무관하게 화면 상에서는 현실과 동일한 시야 보장  

- **앱 전용 UI/아이콘**  
  - 커스텀 SeekBar, 테두리 효과  
  - Mandro Eyes 전용 로고 및 런처 아이콘  
  - 메뉴(`Settings`, `About`) 포함 

---
## 🎬 기능 미리보기

| 간격 | 반전 |
| :----------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------: |
| <img width="478" alt="image" src="https://github.com/user-attachments/assets/95ae975c-c763-4b2c-a1d1-3d1040cd528a"> | <img width="478" alt="image" src="https://github.com/user-attachments/assets/85cbbc54-5f3c-4ba3-af98-4d4cb68f9629"> |

| 순서 | 회전 |
| :----------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------: |
| <img width="478" alt="image" src="https://github.com/user-attachments/assets/f63f6b6e-3575-4b98-8f89-cb27d77fa9e8"> | <img width="478" alt="image" src="https://github.com/user-attachments/assets/81f587cc-e79f-4b0e-b9b9-f2528ea7c078"> |

| 해상도 | 설정 저장 | 
| :----------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------: |
| <img width="478" alt="image" src="https://github.com/user-attachments/assets/202499ed-21a0-40ae-88b3-567c59adfe31"> | <img width="478" alt="image" src="https://github.com/user-attachments/assets/4b2ca003-09cc-4f66-90f8-000efa9e21aa"> |

---
## 🛠 기술 스택

| 구분 | 사용 기술 |
|------|-----------------------------|
| **언어** | Kotlin (Android SDK) |
| **UI** | Android XML |
| **네트워크** | WebView |


## 📂 프로젝트 구조

```bash
mandro-client/
└── app/src/main/
    ├── java/com/example/mandro/
    │   ├── CameraActivity.kt
    │   └── MainActivity.kt
    ├── res/
    │   ├── layout/
    │   │   ├── activity_camera.xml
    │   │   └── activity_main.xml
    │   ├── menu/
    │   │   └── menu_main.xml
    │   ├── drawable/
    │   │   ├── box_border.xml
    │   │   ├── custom_seekbar_progress.xml
    │   │   └── custom_thumb.xml
    │   └── values/
    │       ├── colors.xml
    │       ├── strings.xml
    │       └── themes.xml
    ├── AndroidManifest.xml
    ├── ic_logo-playstore.png
    └── ic_main-playstore.png
```
