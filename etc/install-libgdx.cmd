@echo off
REM ===============================================
REM installs libgdx to your local Maven repository
REM ===============================================

set LIB_DIR=d:\Downloads\libgdx-0.9.6
set GROUP_ID=libgdx
set VERSION=0.9.6

set LIB_GDX=gdx
set LIB_GDX_BACKEND_ANDROID=gdx-backend-android
set LIB_GDX_BACKEND_GWT=gdx-backend-gwt
set LIB_GDX_BACKEND_JOGL=gdx-backend-jogl
set LIB_GDX_BACKEND_JOGL_NATIVES=gdx-backend-jogl-natives
set LIB_GDX_BACKEND_LWJGL=gdx-backend-LWJGL
set LIB_GDX_BACKEND_LWJGL_NATIVES=gdx-backend-lwjgl-natives
set LIB_GDX_NATIVES=gdx-natives
set LIB_GDX_OPENAL=gdx-openal
set LIB_GDX_SETUP_UI=gdx-setup-ui

cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_BACKEND_ANDROID%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_BACKEND_ANDROID% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_BACKEND_GWT%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_BACKEND_GWT% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_BACKEND_JOGL%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_BACKEND_JOGL% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_BACKEND_JOGL_NATIVES%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_BACKEND_JOGL_NATIVES% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_BACKEND_LWJGL%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_BACKEND_LWJGL% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_BACKEND_LWJGL_NATIVES%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_BACKEND_LWJGL_NATIVES% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_NATIVES%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_NATIVES% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_OPENAL%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_OPENAL% -Dversion=%VERSION% -Dpackaging=jar"
cmd /C "mvn install:install-file -Dfile=%LIB_DIR%\%LIB_GDX_SETUP_UI%.jar -DgroupId=%GROUP_ID% -DartifactId=%LIB_GDX_SETUP_UI% -Dversion=%VERSION% -Dpackaging=jar"
