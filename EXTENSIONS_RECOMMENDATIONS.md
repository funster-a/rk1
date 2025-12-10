# 🚀 Essential Extensions for Kotlin Android Development in Cursor

## ⭐ Must-Have Extensions (Install These First!)

### 1. **Kotlin Language**
   - **Extension ID**: `fwcd.kotlin`
   - **Why**: Core Kotlin support - syntax highlighting, code completion, error detection
   - **Install**: `Ctrl+Shift+X` → Search "Kotlin Language" → Install

### 2. **Gradle Language Support**
   - **Extension ID**: `naco-siren.gradle-language-support`
   - **Why**: Essential for editing `build.gradle.kts` files with proper syntax highlighting
   - **Install**: Search "Gradle Language Support"

### 3. **XML Tools**
   - **Extension ID**: `redhat.vscode-xml`
   - **Why**: XML formatting, validation, and IntelliSense for Android layouts
   - **Install**: Search "XML" by Red Hat

### 4. **Error Lens**
   - **Extension ID**: `usernamehw.errorlens`
   - **Why**: Shows errors and warnings inline - saves tons of time!
   - **Install**: Search "Error Lens"

## 🎯 Highly Recommended Extensions

### 5. **Material Icon Theme**
   - **Extension ID**: `pkief.material-icon-theme`
   - **Why**: Beautiful file icons that make navigation easier
   - **Install**: Search "Material Icon Theme"

### 6. **GitLens — Git supercharged**
   - **Extension ID**: `eamodio.gitlens`
   - **Why**: Enhanced Git integration - see who changed what and when
   - **Install**: Search "GitLens"

### 7. **Code Spell Checker**
   - **Extension ID**: `streetsidesoftware.code-spell-checker`
   - **Why**: Catches typos in code, comments, and strings
   - **Install**: Search "Code Spell Checker"

### 8. **Kotlin Debug Adapter**
   - **Extension ID**: `fwcd.kotlin-debug-adapter`
   - **Why**: Basic debugging support for Kotlin (limited but useful)
   - **Install**: Search "Kotlin Debug Adapter"

### 9. **Java Extension Pack** (Optional but helpful)
   - **Extension ID**: `vscjava.vscode-java-pack`
   - **Why**: Java support helps with Android SDK understanding
   - **Note**: Only install if you need Java interop features

## 🎨 Nice-to-Have Extensions

### 10. **Better Comments**
   - **Extension ID**: `aaron-bond.better-comments`
   - **Why**: Color-coded comments (TODO, FIXME, etc.)

### 11. **Bracket Pair Colorizer 2** (or built-in)
   - **Extension ID**: `coenraads.bracket-pair-colorizer-2`
   - **Why**: Makes nested brackets easier to read
   - **Note**: Modern Cursor may have this built-in

### 12. **Indent Rainbow**
   - **Extension ID**: `oderwat.indent-rainbow`
   - **Why**: Color-coded indentation levels

### 13. **Todo Tree**
   - **Extension ID**: `gruntfuggly.todo-tree`
   - **Why**: Shows all TODO/FIXME comments in a tree view

### 14. **Thunder Client** (for API testing if needed)
   - **Extension ID**: `rangav.vscode-thunder-client`
   - **Why**: Test REST APIs without leaving Cursor

## 📦 Quick Install Command

Open Cursor terminal and run (PowerShell):
```powershell
code --install-extension fwcd.kotlin
code --install-extension naco-siren.gradle-language-support
code --install-extension redhat.vscode-xml
code --install-extension usernamehw.errorlens
code --install-extension pkief.material-icon-theme
code --install-extension eamodio.gitlens
code --install-extension streetsidesoftware.code-spell-checker
code --install-extension fwcd.kotlin-debug-adapter
```

## ⚙️ Extension Settings (Optional)

Add to `.vscode/settings.json` for optimal extension behavior:

```json
{
  "errorLens.enabled": true,
  "errorLens.enabledDiagnosticLevels": ["error", "warning"],
  "todo-tree.general.tags": ["TODO", "FIXME", "BUG", "HACK", "NOTE"],
  "material-icon-theme.folders.associations": {
    "gradle": "gradle",
    "build": "build"
  }
}
```

## 🔍 How to Install Extensions

1. **Via UI**:
   - Press `Ctrl+Shift+X` to open Extensions view
   - Search for extension name or ID
   - Click "Install"

2. **Via Command Palette**:
   - Press `Ctrl+Shift+P`
   - Type "Extensions: Install Extensions"
   - Search and install

3. **Via Terminal**:
   - Use `code --install-extension <extension-id>`

## ✅ Verification

After installing, verify extensions are working:
1. Open a `.kt` file - should have syntax highlighting
2. Open `build.gradle.kts` - should have Gradle syntax support
3. Open an XML layout file - should have XML IntelliSense
4. Make a syntax error - Error Lens should show it inline

## 🚨 Troubleshooting

**Extension not working?**
- Restart Cursor
- Check if extension is enabled (Extensions view → gear icon)
- Check Cursor/VS Code version compatibility

**Kotlin extension issues?**
- Ensure Java 11+ is installed
- Check `.vscode/settings.json` has Kotlin configuration
- Try reloading window: `Ctrl+Shift+P` → "Developer: Reload Window"

---

**Pro Tip**: Install extensions one at a time and test them. Too many extensions can slow down Cursor!

