# Cursor IDE Setup for Kotlin Android Development

## 🎯 Overview

This guide will help you set up Cursor IDE for efficient Kotlin Android development, replacing Android Studio.

## 📦 Required Extensions

**👉 See `EXTENSIONS_RECOMMENDATIONS.md` for detailed extension guide!**

### Quick Install (Top 4 Must-Haves):

1. **Kotlin Language** - `fwcd.kotlin`
   - Core Kotlin support - syntax, completion, errors

2. **Gradle Language Support** - `naco-siren.gradle-language-support`
   - Essential for `build.gradle.kts` files

3. **XML Tools** - `redhat.vscode-xml`
   - XML formatting and validation for Android layouts

4. **Error Lens** - `usernamehw.errorlens`
   - Inline error/warning display (huge time saver!)

### Also Recommended:
- **Material Icon Theme** - `pkief.material-icon-theme` (better file icons)
- **GitLens** - `eamodio.gitlens` (enhanced Git)
- **Code Spell Checker** - `streetsidesoftware.code-spell-checker` (catch typos)
- **Kotlin Debug Adapter** - `fwcd.kotlin-debug-adapter` (basic debugging)

**Install via**: `Ctrl+Shift+X` → Search extension name → Install

## ⚙️ Configuration Files

### `.vscode/settings.json` (Workspace Settings)

✅ **Already created!** See `.vscode/settings.json` in your project root.

This file configures:
- Kotlin language server settings
- XML formatting for Android layouts
- File exclusions (build folders, etc.)
- Format on save
- Import organization

### `.vscode/tasks.json` (Build Tasks)

✅ **Already created!** See `.vscode/tasks.json` in your project root.

**Available Tasks** (Press `Ctrl+Shift+P` → "Tasks: Run Task"):
- `Gradle: Build Debug APK` - Builds debug APK
- `Gradle: Clean Build` - Cleans build directory
- `Gradle: Install Debug` - Installs on connected device
- `Gradle: Run Tests` - Runs unit tests
- `Gradle: Lint` - Runs Android Lint

**Note**: Android debugging in Cursor is limited. Use:
- `adb logcat` for logs
- Android Studio for complex debugging
- ADB commands for device management

## 🛠️ Terminal Commands Setup

### Windows PowerShell Profile

Add these aliases to your PowerShell profile for easier Android development:

```powershell
# Add to $PROFILE
function Build-Android { ./gradlew.bat assembleDebug }
function Install-Android { adb install -r app/build/outputs/apk/debug/app-debug.apk }
function Run-Android { adb shell am start -n com.example.rk1/.WelcomeActivity }
function Logcat-Android { adb logcat }
function Clean-Android { ./gradlew.bat clean }
```

### Useful Gradle Commands

```bash
# Build debug APK
./gradlew.bat assembleDebug

# Build release APK
./gradlew.bat assembleRelease

# Clean build
./gradlew.bat clean

# Run tests
./gradlew.bat test

# Install on connected device
./gradlew.bat installDebug

# View dependencies
./gradlew.bat dependencies
```

## 📱 ADB Setup

1. **Install Android SDK Platform Tools**
   - Download from: https://developer.android.com/studio/releases/platform-tools
   - Extract to a folder (e.g., `C:\Android\platform-tools`)
   - Add to PATH: `C:\Android\platform-tools`

2. **Verify Installation**
   ```powershell
   adb version
   adb devices
   ```

3. **Enable USB Debugging on Device**
   - Settings → About Phone → Tap "Build Number" 7 times
   - Settings → Developer Options → Enable "USB Debugging"

## 🔍 Code Intelligence Tips

### 1. **Import Organization**
   - Use `Ctrl+Shift+O` (Windows) to organize imports
   - Configure auto-import in settings

### 2. **Go to Definition**
   - `F12` - Go to definition
   - `Ctrl+Click` - Go to definition
   - `Alt+F12` - Peek definition

### 3. **Find References**
   - `Shift+F12` - Find all references
   - `Ctrl+Shift+F` - Search in all files

### 4. **Refactoring**
   - `F2` - Rename symbol
   - `Ctrl+Shift+R` - Refactor symbol
   - Right-click → Refactor options

## 🚨 Limitations vs Android Studio

### What Cursor/VSCode CAN'T do well:
1. **Visual Layout Editor** - No drag-and-drop UI builder
2. **Advanced Debugging** - Limited breakpoint support
3. **APK Analyzer** - No built-in APK analysis
4. **Device Manager** - No visual device management
5. **Profiler** - No performance profiling tools
6. **Lint Integration** - Limited Android Lint support

### Workarounds:
1. **Layouts**: Edit XML directly (better for learning anyway!)
2. **Debugging**: Use `adb logcat` and print statements
3. **APK Analysis**: Use online tools or command-line tools
4. **Device Management**: Use `adb` commands
5. **Profiling**: Use Android Studio occasionally for profiling
6. **Lint**: Run `./gradlew.bat lint` in terminal

## 📚 Recommended Workflow

### Daily Development:
1. **Code in Cursor** - Write Kotlin/XML code
2. **Build in Terminal** - Use Gradle commands
3. **Test on Device** - Use ADB to install/run
4. **Debug with Logcat** - Use `adb logcat` for logs

### When You Need Android Studio:
- Complex debugging sessions
- Performance profiling
- APK analysis
- Visual layout editing (if needed)

## 🎨 Theme Recommendations

For better Android development experience:

1. **Dark Theme**: `One Dark Pro` or `Material Theme`
2. **Icon Theme**: `Material Icon Theme`
3. **Font**: `Fira Code` or `JetBrains Mono` (with ligatures)

## 📝 Keyboard Shortcuts

### Essential Shortcuts:
- `Ctrl+Shift+P` - Command Palette
- `Ctrl+P` - Quick file open
- `Ctrl+Shift+F` - Search in files
- `Ctrl+\` - Split editor
- `Ctrl+B` - Toggle sidebar
- `Ctrl+` ` - Toggle terminal

### Kotlin-Specific:
- `Ctrl+Space` - Code completion
- `Alt+Enter` - Quick fixes
- `Ctrl+Shift+I` - Format document
- `F12` - Go to definition

## 🔧 Troubleshooting

### Kotlin Language Server Not Working:
1. Check if Kotlin extension is installed
2. Restart Cursor
3. Check `settings.json` for Kotlin configuration

### Gradle Build Fails:
1. Check Java version: `java -version` (should be 11+)
2. Check Gradle wrapper: `./gradlew.bat --version`
3. Clean build: `./gradlew.bat clean`

### ADB Not Found:
1. Check PATH environment variable
2. Restart terminal/Cursor after adding to PATH
3. Verify: `adb version`

## 🎯 Next Steps

1. Install all essential extensions
2. Create `.vscode/settings.json` in your project
3. Set up ADB and add to PATH
4. Test build: `./gradlew.bat assembleDebug`
5. Connect device and test: `adb devices`

## 💡 Pro Tips

1. **Use Terminal Integration**: Cursor's integrated terminal is powerful
2. **Multiple Terminals**: Open multiple terminals for different tasks
3. **Task Runner**: Create tasks in `.vscode/tasks.json` for common commands
4. **Snippets**: Create custom snippets for common Android patterns
5. **Git Integration**: Use GitLens for better version control

## 📖 Additional Resources

- [Kotlin Language Reference](https://kotlinlang.org/docs/home.html)
- [Android Developer Guide](https://developer.android.com/guide)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [ADB Documentation](https://developer.android.com/studio/command-line/adb)

---

**Remember**: Cursor/VSCode is great for coding, but Android Studio is still better for some Android-specific tasks. Use both tools strategically!

